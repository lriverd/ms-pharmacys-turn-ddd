package cl.duamit.security;

import cl.duamit.security.model.Token;
import cl.duamit.security.model.TokenClaim;
import cl.duamit.pharmacy.parser.RutParser;
import cl.duamit.pharmacy.type.EnterpriseSegmentType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Objects;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 04-08-2020
 * @since 1.0.0 - 04-08-2020
 */
@Service
public class JwtTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenService.class);

    private JwtProperties jwtProperties;

    public String build(Token token){
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtProperties.getSecret());
        Key key = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
        return Jwts.builder()
                .setSubject(token.getUserName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getTimeExp()))
                .signWith(SignatureAlgorithm.HS512, key)
                .claim(TokenClaim.CUSTOMER_ID.getName(), token.getCustomerId().toString())
                .claim(TokenClaim.USER_ID.getName(), token.getUserId().toString())
                .claim(TokenClaim.DOC_NUMBER.getName(), token.getCiSerialnumber())
                .claim(TokenClaim.IS_CUSTOMER.getName(), token.isCustomer())
                .claim(TokenClaim.SEGMENT.getName(), token.getSegment())
                .claim(TokenClaim.TEMP_ID.getName(), token.getTempId())
                .compact();
    }

    public Claims parseClaims(String jwt){
        Claims claims = null;
        try{
            jwt = jwt.replaceFirst(JwtProperties.TOKEN_PREFIX, "");
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtProperties.getSecret()))
                    .parseClaimsJws(jwt).getBody();
            return claims;
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
        return claims;
    }

    public Token parse(String jwt) throws Exception{
        Token token = null;
        Claims claims = null;
        try {
            claims = parseClaims(jwt);
            token = new Token();
            token.setCustomerId(RutParser.build().parse(Objects.toString(claims.get(TokenClaim.CUSTOMER_ID.getName()))));
            token.setUserId(RutParser.build().parse(Objects.toString(claims.get(TokenClaim.USER_ID.getName()))));
            token.setCiSerialnumber(claims.get(TokenClaim.DOC_NUMBER.getName(), String.class));
            token.setCustomer(claims.get(TokenClaim.IS_CUSTOMER.getName(), Boolean.class));
            token.setSegment(EnterpriseSegmentType.valueOf(claims.get(TokenClaim.SEGMENT.getName(), String.class)));
            token.setTempId(claims.get(TokenClaim.TEMP_ID.getName(), Integer.class));
        }catch(Exception e){
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
        return token;
    }

    @Autowired
    public void setJwtProperties(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }
}
