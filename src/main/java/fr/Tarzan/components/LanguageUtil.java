package fr.Tarzan.components;

import cn.nukkit.Server;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.Utils;
import fr.Tarzan.Loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class LanguageUtil {

    private static final Config config = Loader.getInstance().getConfig();
    private static Map<String, String> lang;

    static {
        try {
            lang = loadLang(LanguageUtil.class.getClassLoader().getResourceAsStream("lang/" + config.getString("lang", "eng") + ".ini"));
        } catch (Exception exception) {
            exception.printStackTrace();
            Server.getInstance().getPluginManager().disablePlugin(Loader.getInstance());
        }
    }

    public static String translate(String str, Object... params) {
        String baseText = get(str);
        baseText = parseTranslation(baseText != null ? baseText : str);

        for (int i = 0; i < params.length; i++) {
            baseText = baseText.replace("{%" + i + "}", parseTranslation(String.valueOf(params[i])));
        }

        return baseText;
    }

    public static String translate(String str, String... params) {
        String baseText = get(str);
        baseText = parseTranslation(baseText != null ? baseText : str);

        for (int i = 0; i < params.length; i++) {
            baseText = baseText.replace("{%" + i + "}", parseTranslation(params[i]));
        }

        return baseText;
    }

    private static Map<String, String> loadLang(InputStream stream) {
        try {
            String content = Utils.readFile(stream);
            Map<String, String> data = new HashMap<>();

            for (String line : content.split("\n")) {
                if (line.equals("") || line.charAt(0) == '#') {
                    continue;
                }

                int splitIndex = line.indexOf('=');
                if (splitIndex == -1) {
                    continue;
                }

                String key = line.substring(0, splitIndex);
                String value = line.substring(splitIndex + 1);

                data.put(key, value.replace("{%*}", "\n"));
            }

            return data;
        } catch (IOException exception) {
            Server.getInstance().getLogger().logException(exception);
            return null;
        }
    }

    private static String get(String id) {
        if (lang.containsKey(id)) {
            return lang.get(id);
        }

        return id;
    }

    private static String internalGet(String id) {
        if (lang.containsKey(id)) {
            return lang.get(id);
        }

        return null;
    }

    private static String parseTranslation(String text) {
        StringBuilder newString = new StringBuilder();
        text = String.valueOf(text);

        StringBuilder replaceString = null;

        int len = text.length();

        for (int i = 0; i < len; ++i) {
            char c = text.charAt(i);
            if (replaceString != null) {
                if ((c >= 0x30 && c <= 0x39) // 0-9
                        || (c >= 0x41 && c <= 0x5a) // A-Z
                        || (c >= 0x61 && c <= 0x7a) || // a-z
                        c == '.' || c == '-') {
                    replaceString.append(c);
                } else {
                    String t = internalGet(replaceString.substring(1));

                    if (t != null) {
                        newString.append(t);
                    } else {
                        newString.append(replaceString);
                    }

                    replaceString = null;
                    if (c == '%') {
                        replaceString = new StringBuilder(String.valueOf(c));
                    } else {
                        newString.append(c);
                    }
                }
            } else if (c == '%') {
                replaceString = new StringBuilder(String.valueOf(c));
            } else {
                newString.append(c);
            }
        }

        if (replaceString != null) {
            String t = internalGet(replaceString.substring(1));
            if (t != null) {
                newString.append(t);
            } else {
                newString.append(replaceString);
            }
        }
        return newString.toString();
    }
}