package org.ncarneiro.testeauto.utils;

/**
 * Created by Gustavo on 8/29/2015.
 */
public interface Constants {

    public enum GameValidation{ VALID_ONLINE, VALID_OFFLINE, INVALID_OFFLINE, WRONG_TIME, FIRST_OFFLINE };

    public static final class StorageKeys {
        public static final String LAST_VALID_TIME = "lastValidTime";
        public static final String LAST_TIME = "lastTime";
        public static final String VALID_OBJ = "validObj";
        public static final String OFFLINE_OBJ = "offlineObj";
    }
}
