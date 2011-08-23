package com.brewster.hashinganalyzer.elasticsearch.index.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

import com.brewster.hashinganalyzer.lucene.analysis.HashingTokenFilter;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.index.settings.IndexSettings;

public class HashingTokenFilterFactory extends AbstractTokenFilterFactory {

	public final String salt;
	
	@Inject public HashingTokenFilterFactory(Index index, @IndexSettings Settings indexSettings,
			String name, Settings settings) {
		super(index, indexSettings, name, settings);
		salt = settings.get("salt");
		assert salt != null;
	}

	@Override
	public TokenStream create(TokenStream tokenStream) {
		return new HashingTokenFilter(tokenStream, salt);
	}

}
