package cl.duamit.pharmacy.model.requirement;

import cl.duamit.pharmacy.model.requirement.products.*;
import cl.duamit.pharmacy.type.product.AccountType;
import cl.duamit.pharmacy.type.product.CreditType;
import cl.duamit.pharmacy.type.product.ProductContract;
import cl.duamit.pharmacy.type.product.RotativeType;

import java.time.LocalDate;
import java.util.Collections;

public class RequirementMock extends Requirement {
    public PortabilityProducts buildPortabilityPorducts(){
        PortabilityProducts portabilityProducts = new PortabilityProducts();

        PortabilityAccount portabilityAccount = new PortabilityAccount();
        portabilityAccount.setNumber("12345");
        portabilityAccount.setType(AccountType.CTACTE);
        PortabilityAccounts portabilityAccounts = new PortabilityAccounts();
        portabilityAccounts.setAllProducts(false);
        portabilityAccounts.setAccounts(Collections.singletonList(portabilityAccount));

        PortabilityRotative portabilityRotative = new PortabilityRotative();
        portabilityRotative.setMaxDebt(100);
        portabilityRotative.setNumber("123456");
        portabilityRotative.setType(RotativeType.LINCRE);
        portabilityRotative.setPromise(true);
        PortabilityRotatives portabilityRotatives = new PortabilityRotatives();
        portabilityRotatives.setAllProducts(false);
        portabilityRotatives.setRotatives(Collections.singletonList(portabilityRotative));

        PortabilityCredit portabilityCredit = new PortabilityCredit();
        portabilityCredit.setMaxDebt(100);
        portabilityCredit.setNumber("123");
        portabilityCredit.setType(CreditType.CREAUT);
        portabilityCredit.setValidity(LocalDate.now());
        PortabilityCredits portabilityCredits = new PortabilityCredits();
        portabilityCredits.setAllProducts(false);
        portabilityCredits.setCredits(Collections.singletonList(portabilityCredit));

        portabilityProducts.setAccounts(portabilityAccounts);
        portabilityProducts.setRotatives(portabilityRotatives);
        portabilityProducts.setCredits(portabilityCredits);
        portabilityProducts.setAllProducts(false);

        return portabilityProducts;
    }

    public ContractProduct buildContractProducts(){
        ContractProduct contractProducts = new ContractProduct();
        contractProducts.setPayDebts(true);
        contractProducts.setProductsContract(Collections.singletonList(ProductContract.CREAUT));
        return contractProducts;
    }

    public RequirementMock() {
        setCustomer(new CustomerMock());
        setLegalRepresentative(new LegalRepresentativeMock());
        setPortabilityProducts(buildPortabilityPorducts());
        setContractProducts(buildContractProducts());
        setAcceptTerms(true);
    }
}