package by.duallab.task.service.validator;

import by.duallab.task.service.consts.ServiceKeys;
import by.duallab.task.service.generator.TimetableGenerator;

public class ValidateEntryLine {

    private ValidateEntryLine(){
        super();
    }

    private static class SingletonHandler{
        static final ValidateEntryLine instance = new ValidateEntryLine();
    }

    public static ValidateEntryLine getInstance(){
        return ValidateEntryLine.SingletonHandler.instance;
    }
    public boolean validate(String entryLine){
        return entryLine.matches(ServiceKeys.ENTRY_LINE_VALIDATOR_PATTERN);
    }

}
