/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brewster.searchcontrib;

import com.brewster.searchcontrib.elasticsearch.index.analysis.HashingTokenFilterFactory;
import org.elasticsearch.index.analysis.AnalysisModule;

/**
 *
 * @author mat
 */
public class SearchContribAnalysisBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {
    @Override
    public void processTokenFilters(TokenFiltersBindings tokenFiltersBindings) {
        tokenFiltersBindings.processTokenFilter("hashing", HashingTokenFilterFactory.class);
    }
}
