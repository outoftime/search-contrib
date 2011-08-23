package com.brewster.searchcontrib.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.miscellaneous.SingleTokenTokenStream;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.junit.Test;
import static org.junit.Assert.*;

import com.brewster.searchcontrib.lucene.analysis.HashingTokenFilter;

public class HashingTokenFilterFactoryTest {

	@Test
	public void testCreate() {
		final ImmutableSettings.Builder builder = ImmutableSettings.settingsBuilder();
		builder.put("salt", "salty");
		final Settings indexSettings = ImmutableSettings.settingsBuilder().build();
		final Settings settings = builder.build();
		final Index index = new Index("test");
		final TokenFilterFactory factory = new HashingTokenFilterFactory(index, indexSettings, "hashing", settings);
		final String word = "testor";
		final Token token = new Token(word, 0, word.length());
		final TokenStream tokenStream = new SingleTokenTokenStream(token);
		final HashingTokenFilter filter = (HashingTokenFilter) factory.create(tokenStream);
		assertEquals("salty", filter.getSalt());
	}

}
