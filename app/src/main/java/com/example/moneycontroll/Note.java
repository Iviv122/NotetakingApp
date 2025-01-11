package com.example.moneycontroll;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Note implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR();
    private String title;
    private String description;
    private String createDate;

    public Note(String nTitle, String nDescription) {
        if (nTitle == null) {
            throw new IllegalArgumentException("nTitle cannot be null");
        }
        if (nDescription == null) {
            throw new IllegalArgumentException("nDescription cannot be null");
        }
        this.title = nTitle;
        this.description = nDescription;
        this.createDate = LocalDateTime.now().toString();
    }

    public void ChangeTitle(String nTitle) {
        if (nTitle == null) {
            throw new IllegalArgumentException("nTitle cannot be null");
        }
        this.title = nTitle;
    }

    public void ChangeDiscription(String nDisc) {
        if (nDisc == null) {
            throw new IllegalArgumentException("nDisc cannot be null");
        }
        this.description = nDisc;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.description;
    }

    public String getDate() {
        return this.createDate;
    }

    protected Note(Parcel parcel) {
        if (parcel == null) {
            throw new IllegalArgumentException("parcel cannot be null");
        }
        this.title = parcel.readString();
        this.description = parcel.readString();
        // Read the createDate as a String and convert it to LocalDateTime
        String dateString = parcel.readString();
        this.createDate = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        if (parcel == null) {
            throw new IllegalArgumentException("parcel cannot be null");
        }
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.createDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final class CREATOR implements Parcelable.Creator<Note> {
        @Override
        public Note createFromParcel(Parcel parcel) {
            if (parcel == null) {
                throw new IllegalArgumentException("parcel cannot be null");
            }
            return new Note(parcel);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    }
}
