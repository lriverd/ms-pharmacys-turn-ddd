package cl.duamit.bank.usecase;

import cl.duamit.bank.entitie.Bank;
import cl.duamit.pharmacy.repository.requirements.RequirimentsBanksRepository;
import cl.duamit.pharmacy.service.AbstractService;
import cl.duamit.pharmacy.ws.soap.BankServiceWS;
import com.bns.xsd.ib.msgs.bussupt.bldgequipandfac.siteops.banksinfoinq.v1.RetrieveBanksInfoInqRqPayload;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Banks Service
 *
 * @author Luis Riveros
 * @version 1.0.0 - 03-08-2020
 * @since 1.0.0 - 03-08-2020
 */
@Service
@Slf4j
public class BankService extends AbstractService<List<Bank>, Object> {

    private RequirimentsBanksRepository requirimentsBanksRepository;
    private BankServiceWS bankServiceWS;
    private List<Bank> banks;

    /**
     * Get all bank list, if the list is null the load banks again
     *
     * @return
     */
    @Override
    public List<Bank> execute() {
        loadBanksDb();
        return banks;
    }

    /**
     * Get a bank by code
     *
     * @param code
     * @return
     */
    public Bank getByCode(String code) {
        loadBanksDb();
        return banks.stream()
                .filter(b -> code.equals(b.getCode()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Load banks from datapower webservice
     */
    private void loadBanks() {
        banks = new ArrayList<>();
        try {
            banks = bankServiceWS.callWS(new RetrieveBanksInfoInqRqPayload());

        } catch (Exception e) {
            log.error("Error loading banks, this load will be done later", e);
        }
    }

    /**
     * Load banks from database generated
     */
    private void loadBanksDb() {
        if (Objects.nonNull(banks) && !banks.isEmpty()) {
            return;
        }
        banks = new ArrayList<>();
        try {
            banks = requirimentsBanksRepository.getBanks();
        } catch (Exception e) {
            log.error("Error loading banks, this load will be done later", e);
        }
    }

    @Autowired
    public void setBankServiceWS(BankServiceWS bankServiceWS) {
        this.bankServiceWS = bankServiceWS;
    }

    @Autowired
    public void setRequirimentsBanksRepository(RequirimentsBanksRepository requirimentsBanksRepository) {
        this.requirimentsBanksRepository = requirimentsBanksRepository;
    }
}
