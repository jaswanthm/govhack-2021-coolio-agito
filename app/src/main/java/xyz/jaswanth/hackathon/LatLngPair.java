package xyz.jaswanth.hackathon;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

public class LatLngPair implements Parcelable {
    LatLng from;
    LatLng to;
    String title;
    String subtitle;


    public LatLngPair(LatLng from, LatLng to, String title, String subtitle) {
        super();
        this.from = from;
        this.to = to;
        this.title = title;
        this.subtitle = subtitle;

    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }

    protected LatLngPair(Parcel in) {
        from = (LatLng) in.readValue(LatLng.class.getClassLoader());
        to = (LatLng) in.readValue(LatLng.class.getClassLoader());
        title = in.readString();
        subtitle = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(from);
        dest.writeValue(to);
        dest.writeString(title);
        dest.writeString(subtitle);

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LatLngPair> CREATOR = new Parcelable.Creator<LatLngPair>() {
        @Override
        public LatLngPair createFromParcel(Parcel in) {
            return new LatLngPair(in);
        }

        @Override
        public LatLngPair[] newArray(int size) {
            return new LatLngPair[size];
        }
    };
}