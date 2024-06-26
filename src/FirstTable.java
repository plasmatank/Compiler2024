import java.util.ArrayList;
import java.util.HashMap;

public class FirstTable {
    static ArrayList<Formula> formulas;
    static ArrayList<String> terminals;
    static ArrayList<String> nonTerminals;
    static HashMap<String, ArrayList<String>> firsts;

    static void setFirst(ArrayList<Formula> _formulas, ArrayList<String> _terminals,
                         ArrayList<String> _nonTerminals, HashMap<String, ArrayList<String>> _firsts) {
        formulas = _formulas;
        terminals = _terminals;
        nonTerminals = _nonTerminals;
        firsts = _firsts;

        // 终结符全部求出first集
        ArrayList<String> first;
        for (String terminal : terminals) {
            first = new ArrayList<String>();
            first.add(terminal);
            firsts.put(terminal, first);
        }
        // 给所有非终结符注册一下
        for (String nonterminal : nonTerminals) {
            first = new ArrayList<String>();
            firsts.put(nonterminal, first);
        }

        boolean flag;
        while (true) {
            flag = true;
            String left;
            String right;
            String[] rights;
            // 遍历所有文法
            for (Formula formula : formulas) {
                left = formula.returnLeft();
                rights = formula.returnRights();
                // 每个文法的右侧
                for (String s : rights) {
                    right = s;
                    // oneOfRight是否存在，遇到空怎么办
                    if (!right.equals("$")) {   // 右侧的字符串不为空（任意一个都不为空）
                        // 遍历每一个右侧字符串的First集合，即 First(右侧字符串)
                        // 这个就类似于一个递归
                        for (int l = 0; l < firsts.get(right).size(); l++) {
                            // First(left) 包括了 First(oneOfRights)
                            if (firsts.get(left).contains(firsts.get(right).get(l))) {
                                continue;
                            } else {
                                // 不包括, 就加入
                                firsts.get(left).add(firsts.get(right).get(l));
                                flag = false;
                            }
                        }
                    }
                    // OneOfRights -> $
                    if (isCanBeNull(formulas, right)) {
                        continue;
                    } else {
                        break;
                    }
                }
            }
            if (flag) {
                break;
            }
        }
    }

    // 判断是否产生$
    static boolean isCanBeNull(ArrayList<Formula> formulas, String symbol){
        String[] rights;
        // 遍历每一个文法
        for (Formula formula : formulas) {
            // 找到产生式
            if (formula.returnLeft().equals(symbol)) {
                // symbol -> [rights]
                rights = formula.returnRights();
                // symbol -> $
                if (rights[0].equals("$")) {    // 第一个就是$, 即类似于 E -> $
                    return true;
                }
            }
        }
        return false;
    }
}
