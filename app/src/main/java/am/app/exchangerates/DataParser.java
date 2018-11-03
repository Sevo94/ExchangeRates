package am.app.exchangerates;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import am.app.exchangerates.models.BankInfo;
import am.app.exchangerates.models.ExchangeRateBaseModel;

public class DataParser implements JsonDeserializer<ExchangeRateBaseModel> {


    @Override
    public ExchangeRateBaseModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        ExchangeRateBaseModel result = new ExchangeRateBaseModel();

        try {
            final HashMap<String, BankInfo> map = readServiceUrlMap(json.getAsJsonObject());

            if (map != null) {
                result.setBankInfoMap(map);
            }

        } catch (JsonSyntaxException ex) {
            return null;
        }
        return result;
    }


    private HashMap<String, BankInfo> readServiceUrlMap(final JsonObject jsonObject) throws JsonSyntaxException {

        if (jsonObject == null) {
            return null;
        }
        Gson gson = new Gson();

        HashMap<String, BankInfo> products = new HashMap<>();

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {

            String key = entry.getKey();
            BankInfo value = gson.fromJson(entry.getValue(), BankInfo.class);
            products.put(key, value);
        }
        return products;
    }
}
