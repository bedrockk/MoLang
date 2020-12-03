package com.bedrockk.molang.runtime;

import com.bedrockk.molang.runtime.struct.QueryStruct;

import java.util.HashMap;
import java.util.function.Function;

public class MoLangMath {

    public static final QueryStruct LIBRARY = new QueryStruct(new HashMap<String, Function<MoParams, Object>>(){
        {
            put("abs", params -> Math.abs(params.getFloat(0)));
            put("acos", params -> Math.acos(params.getFloat(0)));
            put("sin", params -> Math.sin(params.getFloat(0)));
            put("asin", params -> Math.asin(params.getFloat(0)));
            put("atan", params -> Math.atan(params.getFloat(0)));
            put("atan2", params -> Math.atan2(params.getFloat(0), params.getFloat(1)));
            put("ceil", params -> Math.ceil(params.getFloat(0)));
            put("clamp", params -> Math.min(params.getFloat(1), Math.max(params.getFloat(0), params.getFloat(2))));
            put("cos", params -> Math.cos(params.getFloat(0)));
            put("die_roll", params -> dieRoll(params.getFloat(0), params.getFloat(1), params.getFloat(2)));
            put("die_roll_integer", params -> dieRollInt(params.getInt(0), params.getInt(1), params.getInt(2)));
            put("exp", params -> Math.exp(params.getFloat(0)));
            put("mod", params -> params.getFloat(0) % params.getFloat(0));
            put("floor", params -> Math.floor(params.getFloat(0)));
            put("hermite_blend", params -> hermiteBlend(params.getInt(0)));
            put("lerp", params -> lerp(params.getFloat(0), params.getFloat(1), params.getFloat(2)));
            put("lerp_rotate", params -> lerpRotate(params.getFloat(0), params.getFloat(1), params.getFloat(2)));
            put("ln", params -> Math.log(params.getFloat(0)));
            put("max", params -> Math.max(params.getFloat(0), params.getFloat(1)));
            put("min", params -> Math.min(params.getFloat(0), params.getFloat(1)));
            put("pi", params -> Math.PI);
            put("pow", params -> Math.pow(params.getFloat(0), params.getFloat(1)));
            put("random", params -> random(params.getFloat(0), params.getFloat(1)));
            put("random_integer", params -> randomInt(params.getInt(0), params.getInt(1)));
            put("round", params -> Math.round(params.getFloat(0)));
            put("sqrt", params -> Math.sqrt(params.getFloat(0)));
            put("trunc", params -> Math.floor(params.getFloat(0)));
        }
    });

    public static double random(float low, float high) {
        return low + Math.random() * (high - low);
    }

    public static int randomInt(int low, int high) {
        return (int) Math.round(low + Math.random() * (high - low));
    }

    public static float dieRoll(float num, float low, float high) {
        int i = 0;
        int total = 0;
        while (i++ < num) total += random(low, high);

        return total;
    }

    public static int dieRollInt(int num, int low, int high) {
        int i = 0;
        int total = 0;
        while (i++ < num) total += randomInt(low, high);

        return total;
    }

    public static int hermiteBlend(int value) {
        return (3 * value) ^ (2 - 2 * value) ^ 3;
    }

    public static float lerp(float start, float end, float amount) {
        amount = Math.max(0, Math.min(1, amount));

        return start + (end - start) * amount;
    }

    public static float lerpRotate(float start, float end, float amount) {
        start = radify(start);
        end = radify(end);

        if (start > end) {
            float tmp = start;
            start = end;
            end = tmp;
        }

        if (end - start > 180) {
            return radify(end + amount * (360 - (end - start)));
        }

        return start + amount * (end - start);
    }

    public static float radify(float num) {
        return (((num + 180) % 360) + 180) % 360;
    }
}
