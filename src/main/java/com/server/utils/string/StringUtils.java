package com.server.utils.string;

import cn.hutool.core.text.StrFormatter;
import com.server.constant.Constants;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.util.AntPathMatcher;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/1/25 17:46
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 空格字符的字符串表示。
     */
    public static final String SPACE = " ";

    /**
     * 空字符串，用于表示没有任何字符。
     */
    public static final String EMPTY = "";

    /**
     * Linux和Unix系统中的换行符。
     */
    public static final String LF = "\n";

    /**
     * Windows系统中的回车符。
     */
    public static final String CR = "\r";

    /**
     * HTTP协议的字符串表示，用于构建HTTP请求的URL。
     */
    public static final String HTTP = "http://";

    /**
     * HTTPS协议的字符串表示，用于构建HTTPS请求的URL。
     */
    public static final String HTTPS = "https://";

    /**
     * StringBuilder的初始容量，用于避免在动态扩展时频繁分配内存。
     */
    private static final int STRING_BUILDER_SIZE = 256;

    /**
     * 用于去除字符串中所有音标的正则表达式模式。
     */
    private static final Pattern STRIP_ACCENTS_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

    /**
     * 用于将驼峰命名转换为下划线分隔的命名的分隔符。
     */
    private static final char SEPARATOR = '_';

    /**
     * 表示在字符串中未找到指定字符或子字符串的索引值。
     */
    private static final int INDEX_NOT_FOUND = -1;

    /**
     * 用于限制字符串填充功能的最大填充长度，以避免潜在的内存溢出问题。
     */
    private static final int PAD_LIMIT = 8192;


    /**
     * 判断一个Collection是否为空，包含List、Set、Queue等。
     *
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * 判断一个Collection是否非空，包含List、Set、Queue等。
     *
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 判断一个对象数组是否为空。
     *
     * @param objects 要判断的对象数组
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * 判断一个对象数组是否非空。
     *
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 判断一个Map是否为空。
     *
     * @param map 要判断的Map
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * 判断一个Map是否非空。
     *
     * @param map 要判断的Map
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断一个字符串是否为空串。
     *
     * @param str 要判断的字符串
     * @return true：为空串 false：非空串
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || EMPTY.equals(str.trim());
    }

    /**
     * 判断一个字符串是否为非空串。
     *
     * @param str 要判断的字符串
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    /**
     * 判断一个对象是否为空。
     *
     * @param object 要判断的对象
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * 判断一个对象是否非空。
     *
     * @param object 要判断的对象
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * 判断一个对象是否是数组类型（Java基本型别的数组）。
     *
     * @param object 要判断的对象
     * @return true：是数组 false：不是数组
     */
    public static boolean isArray(Object object) {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * 去除字符串两端的空格。
     *
     * @param str 要处理的字符串
     * @return 去除两端空格后的字符串，如果传入的是null，则返回空字符串
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 截取字符串，从指定位置开始。
     *
     * @param str   要截取的字符串
     * @param start 开始位置，索引从0开始
     * @return 从start位置开始截取的字符串，如果start位置越界，则返回空字符串
     */
    public static String substring(final String str, int start) {
        if (str == null) {
            // 如果字符串为null，返回空字符串
            return EMPTY;
        }

        if (start < 0) {
            // 如果start小于0，则调整start为字符串长度加上start的值
            start = str.length() + start;
        }

        if (start < 0) {
            // 如果调整后的start仍然小于0，则设置start为0
            start = 0;
        }
        if (start > str.length()) {
            // 如果start大于字符串长度，则返回空字符串
            return EMPTY;
        }

        // 从start位置开始截取字符串
        return str.substring(start);
    }

    /**
     * 截取字符串，从指定位置开始到指定位置结束。
     *
     * @param str   要截取的字符串
     * @param start 开始位置，索引从0开始
     * @param end   结束位置，索引从0开始
     * @return 从start位置开始到end位置结束的字符串，如果start或end位置越界，则返回空字符串
     */
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            // 如果字符串为null，返回空字符串
            return EMPTY;
        }

        if (end < 0) {
            // 如果end小于0，则调整end为字符串长度加上end的值
            end = str.length() + end;
        }
        if (start < 0) {
            // 如果start小于0，则调整start为字符串长度加上start的值
            start = str.length() + start;
        }

        if (end > str.length()) {
            // 如果end大于字符串长度，则设置end为字符串长度
            end = str.length();
        }

        if (start > end) {
            // 如果start大于end，则返回空字符串
            return EMPTY;
        }

        if (start < 0) {
            // 如果start小于0，则设置start为0
            start = 0;
        }
        if (end < 0) {
            // 如果end小于0，则设置end为0
            end = 0;
        }

        // 从start位置开始到end位置结束截取字符串
        return str.substring(start, end);
    }


    /**
     * 检查字符串是否以http(s)://开头。
     *
     * @param link 链接字符串
     * @return true：以http(s)://开头 false：不以http(s)://开头
     */
    public static boolean isHttp(String link) {
        return StringUtils.startsWithAny(link, HTTP, HTTPS);
    }

    /**
     * 将字符串转换为Set集合。
     *
     * @param str 字符串，其中包含需要转换的字符
     * @param sep 分隔符，用于分隔字符串中的各个元素
     * @return 包含字符串中各个元素的Set集合
     */
    public static Set<String> str2Set(String str, String sep) {
        // 使用HashSet来存储字符串，因为它提供了快速的插入和查询操作
        return new HashSet<String>(str2List(str, sep, true, false));
    }


    /**
     * 将字符串转换为List集合。
     *
     * @param str         包含需要转换的字符的字符串
     * @param sep         分隔符，用于分隔字符串中的各个元素
     * @param filterBlank 是否过滤纯空白字符串
     * @param trim        是否去除字符串的首尾空白
     * @return 包含字符串中各个元素的List集合
     */
    public static List<String> str2List(String str, String sep, boolean filterBlank, boolean trim) {
        List<String> list = new ArrayList<String>();
        // 如果传入的字符串为空，直接返回一个空的List集合
        if (StringUtils.isEmpty(str)) {
            return list;
        }

        // 如果需要过滤空白字符串，并且传入的字符串是纯空白，则返回一个空的List集合
        if (filterBlank && StringUtils.isBlank(str)) {
            return list;
        }

        // 使用传入的分隔符将字符串分割成数组
        String[] split = str.split(sep);

        // 遍历分割后的数组，如果需要过滤空白字符串，并且当前元素是纯空白，则跳过
        for (String string : split) {
            if (filterBlank && StringUtils.isBlank(string)) {
                continue;
            }

            // 如果需要去除首尾空白，则对当前元素进行trim操作
            if (trim) {
                string = string.trim();
            }

            // 将处理后的元素添加到List集合中
            list.add(string);
        }

        // 返回转换后的List集合
        return list;
    }


    /**
     * 查找指定字符串是否包含指定字符串列表中的任意一个字符串，同时忽略大小写。
     *
     * @param cs                  指定字符串
     * @param searchCharSequences 需要检查的字符串数组
     * @return 是否包含任意一个字符串
     */
    public static boolean containsAnyIgnoreCase(CharSequence cs, CharSequence... searchCharSequences) {
        if (isEmpty(cs) || isEmpty(searchCharSequences)) {
            // 如果指定字符串或搜索字符串数组为空，返回false
            return false;
        }
        for (CharSequence testStr : searchCharSequences) {
            // 如果指定字符串包含搜索字符串，忽略大小写，返回true
            if (containsIgnoreCase(cs, testStr)) {
                return true;
            }
        }
        // 如果指定字符串不包含搜索字符串列表中的任意一个字符串，返回false
        return false;
    }


    /**
     * 将驼峰命名法转换为下划线命名法。
     * 例如：userName -> user_name
     */
    public static String toUnderScoreCase(String str) {
        if (str == null) {
            // 如果输入字符串为null，返回null
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // 记录前一个字符是否为大写
        boolean preCharIsUpperCase = true;
        // 记录当前字符是否为大写
        boolean curreCharIsUpperCase = true;
        // 记录下一个字符是否为大写
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }

            curreCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1)) {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                // 如果前一个字符为大写，当前字符为大写，下一个字符不为大写，则在当前字符前添加下划线
                sb.append(SEPARATOR);
            } else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
                // 如果前一个字符不大写，当前字符为大写，则在当前字符前添加下划线
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }
        // 返回转换后的字符串
        return sb.toString();
    }

    /**
     * 下划线命名 转 驼峰式命名法 例如：user_name -> userName
     *
     * @param s 需要转换的字符串
     * @return 转换后的驼峰式命名字符串
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null; // 如果字符串为null，直接返回null
        }
        s = s.toLowerCase(); // 将字符串转换为小写
        StringBuilder sb = new StringBuilder(s.length()); // 创建一个StringBuilder对象用于构建结果字符串
        boolean upperCase = false; // 标志位，用于标记是否需要将下一个字符转换为大写
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); // 获取当前字符

            if (c == '_') { // 如果字符是下划线
                upperCase = true; // 设置标志位，表示下一个字符需要大写
            } else if (upperCase) { // 如果标志位为true
                sb.append(Character.toUpperCase(c)); // 将当前字符转换为大写并添加到StringBuilder中
                upperCase = false; // 重置标志位
            } else {
                sb.append(c); // 如果不是下划线，直接将字符添加到StringBuilder中
            }
        }
        return sb.toString(); // 将StringBuilder转换为字符串并返回
    }


    /**
     * 检查一个字符串是否包含在字符串数组中，忽略大小写。
     *
     * @param str  要检查的字符串
     * @param strs 包含字符串的数组
     * @return 如果字符串数组中包含该字符串（忽略大小写），则返回true，否则返回false
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) { // 检查传入的字符串和字符串数组是否不为null
            for (String s : strs) { // 遍历字符串数组中的每个字符串
                if (str.equalsIgnoreCase(trim(s))) { // 使用equalsIgnoreCase方法忽略大小写比较，并使用trim方法去除字符串前后的空白字符
                    return true; // 如果找到匹配的字符串，返回true
                }
            }
        }
        return false; // 如果没有找到匹配的字符串，或者传入的参数为null，返回false
    }


    /**
     * 将包含下划线的字符串转换为驼峰式命名法的字符串。
     *
     * @param name 要转换的字符串
     * @return 转换后的驼峰式命名字符串
     */
    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 如果字符串为null或空，则无需转换，直接返回空字符串
            return "";
        } else if (!name.contains("_")) {
            // 如果字符串中不包含下划线，则仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 使用下划线将原始字符串分割成数组
        String[] camels = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线造成的空字符串
            if (camel.isEmpty()) {
                continue;
            }
            // 首字母大写，其余字母小写，然后将结果拼接到result中
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        // 返回转换后的驼峰式命名字符串
        return result.toString();
    }


    /**
     * 检查一个字符串是否与列表中的任意一个字符串匹配。
     *
     * @param str  要检查的字符串
     * @param strs 包含字符串的列表
     * @return 如果字符串与列表中的任意一个字符串匹配，则返回true，否则返回false
     */
    public static boolean matches(String str, List<String> strs) {
        if (isEmpty(str) || isEmpty(strs)) {
            // 如果传入的字符串为空或者列表为空，则没有匹配的可能，返回false
            return false;
        }
        for (String pattern : strs) {
            // 遍历列表中的每个字符串
            if (isMatch(pattern, str)) {
                // 如果找到了匹配的字符串，返回true
                return true;
            }
        }
        // 如果没有找到匹配的字符串，返回false
        return false;
    }

    /**
     * 判断url是否与规则配置:
     * ? 表示单个字符;
     * * 表示一层路径内的任意字符串，不可跨层级;
     * ** 表示任意层路径;
     *
     * @param pattern 要匹配的模式
     * @param url     要检查的字符串
     * @return 如果字符串与模式匹配，则返回true，否则返回false
     */
    public static boolean isMatch(String pattern, String url) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }

    /**
     * 数字左边补齐0，使之达到指定长度。注意，如果数字转换为字符串后，长度大于size，则只保留 最后size个字符。
     *
     * @param num  数字对象
     * @param size 字符串指定长度
     * @return 返回数字的字符串格式，该字符串为指定长度。
     */
    public static String padl(final Number num, final int size) {
        return padl(num.toString(), size, '0');
    }

    /**
     * 字符串左补齐。如果原始字符串s长度大于size，则只保留最后size个字符。
     *
     * @param s    原始字符串
     * @param size 字符串指定长度
     * @param c    用于补齐的字符
     * @return 返回指定长度的字符串，由原字符串左补齐或截取得到。
     */
    public static String padl(final String s, final int size, final char c) {
        final StringBuilder sb = new StringBuilder(size); // 创建一个StringBuilder，预分配大小为size的容量
        if (s != null) { // 检查字符串s是否为null
            final int len = s.length(); // 获取字符串s的长度
            if (len <= size) { // 如果字符串s的长度小于等于size
                for (int i = size - len; i > 0; i--) { // 计算需要补齐的字符数量，并进行循环
                    sb.append(c); // 使用字符c进行左补齐
                }
                sb.append(s); // 将原始字符串s添加到StringBuilder的末尾
            } else {
                return s.substring(len - size, len); // 如果字符串s的长度大于size，则截取最后size个字符并返回
            }
        } else {
            for (int i = size; i > 0; i--) { // 如果字符串s为null，则使用字符c进行完全补齐
                sb.append(c); // 使用字符c进行左补齐
            }
        }
        return sb.toString(); // 将StringBuilder转换为字符串并返回
    }

    /**
     * 将半角的符号转换成全角符号.(即英文字符转中文字符)
     *
     * @param str 源字符串
     * @return 转换后的全角字符串
     */
    public static String changeToFull(String str) {
        // 定义半角字符和全角字符的映射关系
        String source = "1234567890!@#$%^&*()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_=+\\|[];:'\",<.>/?";
        String[] decode = {"１", "２", "３", "４", "５", "６", "７", "８", "９", "０",
                "！", "＠", "＃", "＄", "％", "︿", "＆", "＊", "（", "）", "ａ", "ｂ",
                "ｃ", "ｄ", "ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ", "ｋ", "ｌ", "ｍ", "ｎ",
                "ｏ", "ｐ", "ｑ", "ｒ", "ｓ", "ｔ", "ｕ", "ｖ", "ｗ", "ｘ", "ｙ", "ｚ",
                "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ",
                "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ",
                "Ｙ", "Ｚ", "－", "＿", "＝", "＋", "＼", "｜", "【", "】", "；", "：",
                "'", "\"", "，", "〈", "。", "〉", "／", "？"};
        String result = ""; // 初始化结果字符串
        for (int i = 0; i < str.length(); i++) { // 遍历源字符串中的每个字符
            int pos = source.indexOf(str.charAt(i)); // 查找当前字符在半角字符中的位置
            if (pos != -1) { // 如果找到了对应的半角字符
                result += decode[pos]; // 将对应的全角字符添加到结果字符串中
            } else {
                result += str.charAt(i); // 如果不是半角字符，则直接添加到结果字符串中
            }
        }
        return result; // 返回转换后的全角字符串
    }


    /**
     * 首字母变小写
     *
     * @param str 源字符串
     * @return 首字母转换为小写后的字符串
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0); // 获取字符串的第一个字符
        if (firstChar >= 'A' && firstChar <= 'Z') { // 检查第一个字符是否为大写字母
            char[] arr = str.toCharArray(); // 将字符串转换为字符数组
            arr[0] += ('a' - 'A'); // 将第一个字符转换为小写
            return new String(arr); // 返回新的字符串
        }
        return str; // 如果第一个字符不是大写字母，则直接返回原字符串
    }

    /**
     * 首字母变大写
     *
     * @param str 源字符串
     * @return 首字母转换为大写后的字符串
     */
    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0); // 获取字符串的第一个字符
        if (firstChar >= 'a' && firstChar <= 'z') { // 检查第一个字符是否为小写字母
            char[] arr = str.toCharArray(); // 将字符串转换为字符数组
            arr[0] -= ('a' - 'A'); // 将第一个字符转换为大写
            return new String(arr); // 返回新的字符串
        }
        return str; // 如果第一个字符不是小写字母，则直接返回原字符串
    }


    /**
     * 消除转义字符
     *
     * @param str 源字符串
     * @return 转换为XML转义字符后的字符串
     */
    public static String escapeXML(String str) {
        if (str == null) {
            return ""; // 如果字符串为null，返回空字符串
        }
        StringBuilder sb = new StringBuilder(); // 创建一个StringBuilder来构建结果字符串
        for (int i = 0; i < str.length(); ++i) { // 遍历源字符串中的每个字符
            char c = str.charAt(i); // 获取当前字符
            switch (c) { // 根据当前字符的不同，执行不同的操作
                case '\u00FF': // 代替无效字符
                case '\u0024': // 代替无效字符
                    break; // 跳过这些字符，不添加到结果中
                case '&': // XML中的&字符
                    sb.append("&amp;"); // 转换为XML转义字符
                    break;
                case '<': // XML中的<字符
                    sb.append("&lt;"); // 转换为XML转义字符
                    break;
                case '>': // XML中的>字符
                    sb.append("&gt;"); // 转换为XML转义字符
                    break;
                case '\"': // XML中的"字符
                    sb.append("&quot;"); // 转换为XML转义字符
                    break;
                case '\'': // XML中的'字符
                    sb.append("&apos;"); // 转换为XML转义字符
                    break;
                default: // 其他字符
                    if (c >= '\u0000' && c <= '\u001F') { // 控制字符（ASCII 0-31）
                        break; // 跳过这些字符，不添加到结果中
                    }
                    if (c >= '\uE000' && c <= '\uF8FF') { // 私有使用区域（Private Use Area）
                        break; // 跳过这些字符，不添加到结果中
                    }
                    if (c >= '\uFFF0' && c <= '\uFFFF') { // 代替无效字符
                        break; // 跳过这些字符，不添加到结果中
                    }
                    sb.append(c); // 将当前字符添加到结果中
                    break;
            }
        }
        return sb.toString(); // 返回转换后的字符串
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) { // 检查字符串是否为空或空白
            return ""; // 如果为空或空白，返回空字符串
        }
        String regEx = "<.+?>"; // 正则表达式匹配HTML标签
        Pattern p = Pattern.compile(regEx); // 编译正则表达式
        Matcher m = p.matcher(html); // 创建Matcher对象来匹配正则表达式
        String s = m.replaceAll(""); // 使用Matcher的replaceAll方法替换所有匹配到的HTML标签
        return s; // 返回替换后的字符串
    }

    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return 截取后的字符串
     */
    public static String abbr(String str, int length) {
        if (str == null) { // 检查字符串是否为null
            return ""; // 如果为null，返回空字符串
        }
        try {
            StringBuilder sb = new StringBuilder(); // 创建一个StringBuilder来构建结果字符串
            int currentLength = 0; // 初始化当前长度
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) { // 遍历字符串，并替换HTML标签
                currentLength += String.valueOf(c).getBytes("GBK").length; // 计算当前字符的字节长度
                if (currentLength <= length - 3) { // 判断是否还能添加更多字符
                    sb.append(c); // 如果可以，添加当前字符
                } else {
                    sb.append("..."); // 如果不能，添加省略号
                    break; // 结束循环
                }
            }
            return sb.toString(); // 返回构建好的缩略字符串
        } catch (UnsupportedEncodingException e) { // 处理不支持的编码异常
            e.printStackTrace(); // 打印异常堆栈信息
        }
        return ""; // 如果出现异常，返回空字符串
    }


    /**
     * 缩略字符串（替换html）
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return 缩略后的字符串
     */
    public static String rabbr(String str, int length) {
        // 使用replaceHtml方法替换字符串中的HTML标签
        String cleanStr = replaceHtml(str);
        // 使用abbr方法缩略字符串，使其长度不超过指定的length
        return abbr(cleanStr, length);
    }


    /**
     * 将对象转换为Double类型。
     *
     * @param val 要转换的对象
     * @return 对象的Double表示，如果转换失败则返回0D
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            // 如果对象为null，返回0D
            return 0D;
        }
        try {
            // 尝试将对象的字符串表示转换为Double，并去除前后空格
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            // 如果转换过程中发生异常，返回0D
            return 0D;
        }
    }

    /**
     * 将对象转换为Float类型。
     *
     * @param val 要转换的对象
     * @return 对象的Float表示，如果转换失败则返回0F
     */
    public static Float toFloat(Object val) {
        // 先将对象转换为Double，然后转换为Float
        return toDouble(val).floatValue();
    }

    /**
     * 将对象转换为Long类型。
     *
     * @param val 要转换的对象
     * @return 对象的Long表示，如果转换失败则返回0L
     */
    public static Long toLong(Object val) {
        // 先将对象转换为Double，然后转换为Long
        return toDouble(val).longValue();
    }

    /**
     * 将对象转换为Integer类型。
     *
     * @param val 要转换的对象
     * @return 对象的Integer表示，如果转换失败则返回0
     */
    public static Integer toInteger(Object val) {
        // 先将对象转换为Long，然后转换为Integer
        return toLong(val).intValue();
    }

    /**
     * 将对象数组转换为以逗号分隔的字符串。
     *
     * @param arr 要转换的对象数组
     * @return 以逗号分隔的字符串表示的数组
     */
    public static String arrayToCommaDelimitedString(Object[] arr) {
        // 调用arrayToDelimitedString方法，使用逗号作为分隔符
        return arrayToDelimitedString(arr, ",");
    }

    /**
     * 将对象数组转换为以指定分隔符分隔的字符串。
     *
     * @param arr   要转换的对象数组
     * @param delim 分隔符字符串
     * @return 以指定分隔符分隔的字符串表示的数组
     */
    public static String arrayToDelimitedString(Object[] arr, String delim) {
        if (arr == null) {
            // 如果数组为null，返回null
            return null;
        }
        if (arr.length == 0) {
            // 如果数组长度为0，返回空字符串
            return "";
        }
        StringBuilder sb = new StringBuilder();
        // 添加数组的第一个元素
        sb.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            // 从数组的第二个元素开始，添加分隔符和元素
            sb.append(delim);
            sb.append(arr[i]);
        }
        // 返回构建的字符串
        return sb.toString();
    }

    /**
     * 将集合转换为以逗号分隔的字符串。
     *
     * @param collection 要转换的集合
     * @return 以逗号分隔的字符串表示的集合
     */
    public static String collectionToCommaDelimitedString(Collection<?> collection) {
        // 调用collectionToDelimitedString方法，使用逗号作为分隔符
        return collectionToDelimitedString(collection, ",");
    }

    /**
     * 将集合转换为以指定分隔符分隔的字符串。
     *
     * @param collection 要转换的集合
     * @param delimiter  分隔符字符串
     * @return 以指定分隔符分隔的字符串表示的集合
     */
    public static String collectionToDelimitedString(Collection<?> collection, String delimiter) {
        // 调用collectionToDelimitedString方法，使用空字符串作为前缀和后缀
        return collectionToDelimitedString(collection, delimiter, "", "");
    }

    /**
     * 将集合转换为以指定分隔符分隔的字符串，并可以在每个元素前后添加前缀和后缀。
     *
     * @param collection 要转换的集合
     * @param delimiter  分隔符字符串
     * @param prefix     每个元素前添加的前缀
     * @param suffix     每个元素后添加的后缀
     * @return 以指定分隔符分隔的字符串表示的集合
     */
    public static String collectionToDelimitedString(Collection<?> collection, String delimiter, String prefix, String suffix) {
        if (StringUtils.isEmpty(collection)) {
            // 如果集合为空或null，返回空字符串
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            // 遍历集合，添加前缀、元素和后缀
            sb.append(prefix).append(iterator.next()).append(suffix);
            if (iterator.hasNext()) {
                // 如果还有下一个元素，添加分隔符
                sb.append(delimiter);
            }
        }
        // 返回构建的字符串
        return sb.toString();
    }


    /**
     * 将输入的逗号分隔字符串转换为适用于SQL IN查询的字符串形式。
     *
     * @param input 以逗号分隔的字符串
     * @return 适合SQL IN查询的字符串，每个元素都被单引号包围，并以逗号分隔
     */
    public static String toInQueryString(String input) {
        // 将输入字符串按照逗号分隔成数组
        String[] items = input.split(",");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            // 将每个元素前后添加单引号，以便在SQL查询中使用
            sb.append("'").append(items[i]).append("'");
            // 如果当前元素不是数组中的最后一个元素，则添加逗号分隔符
            if (i < items.length - 1) {
                sb.append(",");
            }
        }
        // 返回构建的适用于SQL IN查询的字符串
        return sb.toString();
    }

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params   参数值
     * @return 格式化后的文本
     */
    public static String format(String template, Object... params) {
        if (isEmpty(params) || isEmpty(template)) {
            return template;
        }
        return StrFormatter.format(template, params);
    }



    /**
     * 是否为http(s)://开头
     *
     * @param link 链接
     * @return 结果
     */
    public static boolean ishttp(String link)
    {
        return StringUtils.startsWithAny(link, Constants.HTTP, Constants.HTTPS);
    }

    /**
     * 截取指定子字符串之前的字符串
     * @param str 原始字符串
     * @param subStr 子字符串
     * @return 子字符串之前的字符串，如果未找到子字符串，则返回空字符串
     */
    public static String substringBefore(String str, String subStr) {
        if (str != null && subStr != null) {
            int index = str.indexOf(subStr);
            if (index != -1) {
                return str.substring(0, index);
            }
        }
        return "";
    }

    /**
     * 截取指定子字符串之后的字符串
     * @param str 原始字符串
     * @param subStr 子字符串
     * @return 子字符串之后的字符串，如果未找到子字符串，则返回空字符串
     */
    public static String substringAfter(String str, String subStr) {
        if (str != null && subStr != null) {
            int index = str.indexOf(subStr);
            if (index != -1) {
                return str.substring(index + subStr.length());
            }
        }
        return "";
    }

    /**
     * 截取两个子字符串之间的字符串
     * @param str 原始字符串
     * @param startSubStr 起始子字符串
     * @param endSubStr 结束子字符串
     * @return 两个子字符串之间的字符串，如果未找到子字符串，则返回空字符串
     */
    public static String substringBetween(String str, String startSubStr, String endSubStr) {
        if (str != null && startSubStr != null && endSubStr != null) {
            int startIndex = str.indexOf(startSubStr);
            int endIndex = str.indexOf(endSubStr);
            if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
                return str.substring(startIndex + startSubStr.length(), endIndex);
            }
        }
        return "";
    }
}
