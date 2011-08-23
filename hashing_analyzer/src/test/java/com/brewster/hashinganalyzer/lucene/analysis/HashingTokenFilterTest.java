package com.brewster.hashinganalyzer.lucene.analysis;

import static org.apache.commons.codec.digest.DigestUtils.shaHex;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.miscellaneous.SingleTokenTokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import com.brewster.hashinganalyzer.lucene.analysis.HashingTokenFilter;

public class HashingTokenFilterTest {

	@Test
	public void testIncrementToken() throws IOException {
		final String word = "testor";
		final Token token = new Token(word, 0, word.length());
		final TokenStream tokenStream = new SingleTokenTokenStream(token);
		final TokenFilter filter = new HashingTokenFilter(tokenStream, "salty");
		assertTrue(filter.incrementToken());
		final CharTermAttribute attribute = tokenStream.getAttribute(CharTermAttribute.class);
		assertEquals(shaHex("testorsalty").substring(0, 7), attribute.toString());
	}

}
