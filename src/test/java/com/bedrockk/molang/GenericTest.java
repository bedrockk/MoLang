package com.bedrockk.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.bedrockk.molang.runtime.value.StringValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Generic Tests")
public class GenericTest {

	@Test
	@DisplayName("MoParams Test")
	public void testMoParams() {
		var value = new StringValue("AAAAAAAAA");
		var params = new MoParams(List.of(value));
		Assertions.assertEquals(value.asString(), params.getString(0));
	}
}
