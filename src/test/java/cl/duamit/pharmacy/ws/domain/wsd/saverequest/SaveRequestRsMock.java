package cl.duamit.pharmacy.ws.domain.wsd.saverequest;

public class SaveRequestRsMock extends SaveRequestRs {

    public SaveRequestRsMock() {
        sethNameRequestRegister("Name");
        sethUserRequestRegister("User");
        setId(123);
        setStage(buildSaveRequestStageItem());
    }

    public SaveRequestStageItem buildSaveRequestStageItem() {
        SaveRequestStageItem item = new SaveRequestStageItem();
        item.setAssociatedCellId("123");
        item.setAssociatedCellName("Exname");
        return item;
    }
}