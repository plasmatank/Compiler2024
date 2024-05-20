public class Config {
    static String grammarPath = ".\\..\\input\\grammar.txt";
    static String lexInputPath = ".\\..\\input\\07_var_defn.sy";

    static String formulaPath = ".\\..\\output\\文法.tsv";
    static String firstTablePath = ".\\..\\output\\First集合.tsv";
    static String followTablePath = ".\\..\\output\\Follow集合.tsv";
    static String predictMapPath = ".\\..\\output\\分析表.tsv";
    static String terminalPath = ".\\..\\output\\终结符.tsv";
    static String nonTerminalPath = ".\\..\\output\\非终结符.tsv";
    static String lexiconMiddleResult = ".\\..\\output\\词法分析产生的中间结果.tsv";
    static String parseResultPath = ".\\..\\result\\gra.tsv";
    static String lexiconResultPath = ".\\..\\result\\lex.tsv";

    static String initSymbol = "program"; // 入口文法！
}
