package com.juaracoding.tajuaracoding.pages.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class CompositeActionBuilder {
    private List<CompositeAction> actions = new ArrayList<>();
    
    public CompositeActionBuilder navigateTo(String menuType) {
        actions.add(new NavigationAction(menuType));
        return this;
    }
    
    public CompositeActionBuilder verifyPage(String expectedUrlFragment, String... requiredElements) {
        actions.add(new PageVerificationAction(expectedUrlFragment, requiredElements));
        return this;
    }
    
    public CompositeActionBuilder search(String keyword, boolean shouldFindResults) {
        actions.add(new SearchAction(keyword, shouldFindResults));
        return this;
    }
    
    public CompositeActionBuilder filterByDate(String month, int startIndex, int endIndex, String expectedStart, String expectedEnd) {
        actions.add(new DateFilterAction(month, startIndex, endIndex, expectedStart, expectedEnd));
        return this;
    }
    
    public CompositeActionBuilder filterByUnit(String unitName, boolean shouldFindResults) {
        actions.add(new UnitFilterAction(unitName, shouldFindResults));
        return this;
    }
    
    public CompositeActionBuilder resetFilters() {
        actions.add(new ResetFilterAction());
        return this;
    }

    public CompositeActionBuilder statusCheckAction(String... expectedStatuses) {
        actions.add(new StatusCheckAction(Arrays.asList(expectedStatuses)));
        return this;
    }

    public CompositeActionBuilder totalDataAction(String jsonResponse, String... entityNames) {
        actions.add(new TotalDataAction(entityNames));
        return this;
    }

    public CompositeActionBuilder captureDataBefore(String... entities) {
        actions.add(new TotalDataAction(TotalDataAction.ValidationType.BEFORE_FILTER, entities));
        return this;
    }

    public CompositeActionBuilder captureDataAfter(String... entities) {
        actions.add(new TotalDataAction(TotalDataAction.ValidationType.AFTER_FILTER, entities));
        return this;
    }

    public CompositeActionBuilder validateFilterReduction(String... entities) {
        actions.add(new TotalDataAction(TotalDataAction.ValidationType.COMPARISON, entities));
        return this;
    }

    public CompositeActionBuilder filterCutiByUnit(String unitName, boolean expectResults) {
        actions.add(new CutiFilterAction(unitName, expectResults));
        return this;
    }

    public CompositeActionBuilder filterCutiByUnit(String unitName) {
        actions.add(new CutiFilterAction(unitName, true));
        return this;
    }

    public CompositeActionBuilder filterCutiByUnitExpectEmpty(String unitName) {
        actions.add(new CutiFilterAction(unitName, false));
        return this;
    }

    public CompositeActionBuilder filterCutiByUnitWithValidation(String unitName, boolean expectResults, 
                                                                java.util.function.Consumer<CutiFilterAction> callback) {
        actions.add(new CompositeAction() {
            @Override
            public void execute() {
                CutiFilterAction cutiFilter = new CutiFilterAction(unitName, expectResults);
                cutiFilter.execute();
                
                if (callback != null) {
                    callback.accept(cutiFilter);
                }
            }
            
            @Override
            public boolean isCompleted() {
                return true;
            }
            
            @Override
            public String getResult() {
                return "✅ Custom cuti filter with validation completed";
            }
        });
        return this;
    }

    public void execute() {
        for (CompositeAction action : actions) {
            action.execute();
            if (!action.isCompleted()) {
                throw new RuntimeException("Action failed: " + action.getResult());
            }
            System.out.println("✓ " + action.getResult());
        }
    }
    
    public List<String> getResults() {
        List<String> results = new ArrayList<>();
        for (CompositeAction action : actions) {
            results.add(action.getResult());
        }
        return results;
    }

    public CompositeActionBuilder exportAction() {
        actions.add(new ExportAction());
        return this;
    }

    public CompositeActionBuilder exportCancel() {
        actions.add(new ExportCancel());
        return this;
    }

    public CompositeActionBuilder sleep(long millis) {
        actions.add(new SleepAction(millis));
        return this;
    }
}
