/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brewster.searchcontrib;

import org.elasticsearch.common.inject.Module;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.plugins.AbstractPlugin;

/**
 *
 * @author mat
 */
public class SearchContribPlugin extends AbstractPlugin {
    @Override
    public String name() {
        return "SearchContribPlugin";
    }

    @Override
    public String description() {
        return "Brewster extensions to Lucene and ElasticSearch";
    }
    
    @Override
    public void processModule(Module module) {
        if (module instanceof AnalysisModule) {
            final AnalysisModule analysisModule = (AnalysisModule) module;
            analysisModule.addProcessor(new SearchContribAnalysisBinderProcessor());
        }
    }
    
}
