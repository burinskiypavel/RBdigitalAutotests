package Gson;

import com.google.gson.annotations.SerializedName;

public class Parcel {
    @SerializedName("class")
    Data[] data;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Parcel [data=");

        for(Data singleData : data) {
            if (singleData != data[0]) {
                result.append(", ");
            }

            result.append(singleData.toString());
        }

        result.append("]");

        return result.toString();
    }
}
