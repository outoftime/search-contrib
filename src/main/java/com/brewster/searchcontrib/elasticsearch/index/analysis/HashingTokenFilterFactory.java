package com.brewster.searchcontrib.elasticsearch.index.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

import com.brewster.searchcontrib.lucene.analysis.HashingTokenFilter;

public class HashingTokenFilterFactory extends AbstractTokenFilterFactory {

	public final String salt;
	
	public HashingTokenFilterFactory(Index index, Settings indexSettings,
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
