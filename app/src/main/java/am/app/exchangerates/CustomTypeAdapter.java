package am.app.exchangerates;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import am.app.exchangerates.models.BankInfo;
import am.app.exchangerates.models.ExchangeRateBaseModel;

public class CustomTypeAdapter extends TypeAdapter<ExchangeRateBaseModel> {

    @Override
    public void write(JsonWriter out, ExchangeRateBaseModel value) throws IOException {

    }

    @Override
    public ExchangeRateBaseModel read(JsonReader in) throws IOException {

        ExchangeRateBaseModel result = new ExchangeRateBaseModel();

        try {
            in.beginObject();

            while (in.hasNext()) {

            }

//            if (map != null) {
//                result.setBankInfoMap(map);
//            }

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
