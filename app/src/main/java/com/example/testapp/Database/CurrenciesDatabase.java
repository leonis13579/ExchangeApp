package com.example.testapp.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.testapp.Database.Dao.CurrenciesDao;
import com.example.testapp.mvvm.models.Currencies;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Currencies.class}, version = 1, exportSchema = false)
public abstract class CurrenciesDatabase extends RoomDatabase {

        public abstract CurrenciesDao currenciesDao();

        private static volatile CurrenciesDatabase INSTANCE;
        private static final int NUMBER_OF_THREADS = 4;

        private static RoomDatabase.Callback sRoomDatabaseCallback = new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                databaseWriteExecutor.execute(() -> {
                    CurrenciesDao dao = INSTANCE.currenciesDao();
                    dao.deleteAll();

                    Currencies[] currencies = {
                            new Currencies("USD"),
                            new Currencies("AED"),
                            new Currencies("AFN"),
                            new Currencies("ALL"),
                            new Currencies("AMD"),
                            new Currencies("ANG"),
                            new Currencies("AOA"),
                            new Currencies("ARS"),
                            new Currencies("AUD"),
                            new Currencies("AWG"),
                            new Currencies("AZN"),
                            new Currencies("BAM"),
                            new Currencies("BBD"),
                            new Currencies("BDT"),
                            new Currencies("BGN"),
                            new Currencies("BHD"),
                            new Currencies("BIF"),
                            new Currencies("BMD"),
                            new Currencies("BND"),
                            new Currencies("BOB"),
                            new Currencies("BRL"),
                            new Currencies("BSD"),
                            new Currencies("BTN"),
                            new Currencies("BWP"),
                            new Currencies("BYN"),
                            new Currencies("BZD"),
                            new Currencies("CAD"),
                            new Currencies("CDF"),
                            new Currencies("CHF"),
                            new Currencies("CLP"),
                            new Currencies("CNY"),
                            new Currencies("COP"),
                            new Currencies("CRC"),
                            new Currencies("CUC"),
                            new Currencies("CUP"),
                            new Currencies("CVE"),
                            new Currencies("CZK"),
                            new Currencies("DJF"),
                            new Currencies("DKK"),
                            new Currencies("DOP"),
                            new Currencies("DZD"),
                            new Currencies("EGP"),
                            new Currencies("ERN"),
                            new Currencies("ETB"),
                            new Currencies("EUR"),
                            new Currencies("FJD"),
                            new Currencies("FKP"),
                            new Currencies("FOK"),
                            new Currencies("GBP"),
                            new Currencies("GEL"),
                            new Currencies("GGP"),
                            new Currencies("GHS"),
                            new Currencies("GIP"),
                            new Currencies("GMD"),
                            new Currencies("GNF"),
                            new Currencies("GTQ"),
                            new Currencies("GYD"),
                            new Currencies("HKD"),
                            new Currencies("HNL"),
                            new Currencies("HRK"),
                            new Currencies("HTG"),
                            new Currencies("HUF"),
                            new Currencies("IDR"),
                            new Currencies("ILS"),
                            new Currencies("IMP"),
                            new Currencies("INR"),
                            new Currencies("IQD"),
                            new Currencies("IRR"),
                            new Currencies("ISK"),
                            new Currencies("JMD"),
                            new Currencies("JOD"),
                            new Currencies("JPY"),
                            new Currencies("KES"),
                            new Currencies("KGS"),
                            new Currencies("KHR"),
                            new Currencies("KID"),
                            new Currencies("KMF"),
                            new Currencies("KRW"),
                            new Currencies("KWD"),
                            new Currencies("KYD"),
                            new Currencies("KZT"),
                            new Currencies("LAK"),
                            new Currencies("LBP"),
                            new Currencies("LKR"),
                            new Currencies("LRD"),
                            new Currencies("LSL"),
                            new Currencies("LYD"),
                            new Currencies("MAD"),
                            new Currencies("MDL"),
                            new Currencies("MGA"),
                            new Currencies("MKD"),
                            new Currencies("MMK"),
                            new Currencies("MNT"),
                            new Currencies("MOP"),
                            new Currencies("MRU"),
                            new Currencies("MUR"),
                            new Currencies("MVR"),
                            new Currencies("MWK"),
                            new Currencies("MXN"),
                            new Currencies("MYR"),
                            new Currencies("MZN"),
                            new Currencies("NAD"),
                            new Currencies("NGN"),
                            new Currencies("NIO"),
                            new Currencies("NOK"),
                            new Currencies("NPR"),
                            new Currencies("NZD"),
                            new Currencies("OMR"),
                            new Currencies("PAB"),
                            new Currencies("PEN"),
                            new Currencies("PGK"),
                            new Currencies("PHP"),
                            new Currencies("PKR"),
                            new Currencies("PLN"),
                            new Currencies("PYG"),
                            new Currencies("QAR"),
                            new Currencies("RON"),
                            new Currencies("RSD"),
                            new Currencies("RUB"),
                            new Currencies("RWF"),
                            new Currencies("SAR"),
                            new Currencies("SBD"),
                            new Currencies("SCR"),
                            new Currencies("SDG"),
                            new Currencies("SEK"),
                            new Currencies("SGD"),
                            new Currencies("SHP"),
                            new Currencies("SLL"),
                            new Currencies("SOS"),
                            new Currencies("SRD"),
                            new Currencies("SSP"),
                            new Currencies("STN"),
                            new Currencies("SYP"),
                            new Currencies("SZL"),
                            new Currencies("THB"),
                            new Currencies("TJS"),
                            new Currencies("TMT"),
                            new Currencies("TND"),
                            new Currencies("TOP"),
                            new Currencies("TRY"),
                            new Currencies("TTD"),
                            new Currencies("TVD"),
                            new Currencies("TWD"),
                            new Currencies("TZS"),
                            new Currencies("UAH"),
                            new Currencies("UGX"),
                            new Currencies("UYU"),
                            new Currencies("UZS"),
                            new Currencies("VES"),
                            new Currencies("VND"),
                            new Currencies("VUV"),
                            new Currencies("WST"),
                            new Currencies("XAF"),
                            new Currencies("XCD"),
                            new Currencies("XDR"),
                            new Currencies("XOF"),
                            new Currencies("XPF"),
                            new Currencies("YER"),
                            new Currencies("ZAR"),
                            new Currencies("ZMW")
                    };

                    for(Currencies currency : currencies) {
                        dao.insert(currency);
                    }
                });
            }
        };

        public static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        public static CurrenciesDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (CurrenciesDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                CurrenciesDatabase.class,
                                "currencies_database")
                                .addCallback(sRoomDatabaseCallback)
                                .build();
                    }
                }
            }
            return INSTANCE;
        }
}
