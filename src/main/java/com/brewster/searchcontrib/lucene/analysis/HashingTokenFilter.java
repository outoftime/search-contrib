package com.brewster.searchcontrib.lucene.analysis;

import java.io.IOException;

import static org.apache.commons.codec.digest.DigestUtils.shaHex;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public final class HashingTokenFilter extends TokenFilter {
	private final String salt;
	private final CharTermAttribute termAttribute;

	public HashingTokenFilter(TokenStream input, String initSalt) {
		super(input);
		assert initSalt != null;
		salt = initSalt;
		termAttribute = addAttribute(CharTermAttribute.class);
	}

	@Override
	public boolean incrementToken() throws IOException {
		if (!input.incrementToken()) {
			return false;
		}
		final String termText = termAttribute.toString();
		final String saltedTermText = termText + salt;
		final String hashedToken = shaHex(saltedTermText).substring(0, 7);
		termAttribute.setEmpty();
		termAttribute.append(hashedToken);
		return true;
	}

	public String getSalt() {
		return this.salt;
	}

}
