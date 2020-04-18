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
        String getter = get(str);
        String baseText = parseTranslation(getter != null ? getter : str);

        for (int i = 0; i < params.length; i++) {
            baseText = baseText.replace("{%" + i + "}", parseTranslation(String.valueOf(params[i])));
        }

        return baseText;
    }

    public static String translate(String str, String... params) {
        String getter = get(str);
        String baseText = parseTranslation(getter != null ? getter : str);

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
        } catch (Exception exception) {
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
        StringBuilder replaceString = null;

        int length = text.length();

        for (int i = 0; i < length; ++i) {
            char charAt = text.charAt(i);
            if (replaceString != null) {
                if ((charAt >= 0x30 && charAt <= 0x39) // 0-9
                        || (charAt >= 0x41 && charAt <= 0x5a) // A-Z
                        || (charAt >= 0x61 && charAt <= 0x7a) || // a-z
                        charAt == '.' || charAt == '-') {
                    replaceString.append(charAt);
                } else {
                    String internalGet = internalGet(replaceString.substring(1));

                    if (internalGet != null) {
                        newString.append(internalGet);
                    } else {
                        newString.append(replaceString);
                    }

                    replaceString = charAt == '%' ? new StringBuilder(String.valueOf(charAt)) : null;
                    if (replaceString == null) {
                        newString.append(charAt);
                    }
                }
            } else if (charAt == '%') {
                replaceString = new StringBuilder(String.valueOf(charAt));
            } else {
                newString.append(charAt);
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