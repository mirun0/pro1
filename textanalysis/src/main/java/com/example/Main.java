package com.example;

import java.util.Map;

import com.example.analysis.CharAnalyzer;
import com.example.analysis.WordAnalyzer;

public class Main {

    public static String data = "FIM UHK byla založena roku 1993, kdy začínala se 78 studenty. Nyní má na svém kontě již přes 9 tisíc absolventů. Fakulta svým studentům nabízí příjemné, moderní a špičkově technologicky vybavené prostředí. Studium probíhá v oborech informatika, management, ekonomie, cestovní ruch a datová věda. Důraz je kladen na inteligentní systémy, využívání chytrých přístupů a technologií v manažerském rozhodování a Data Science. Na fakultu každoročně zavítá na 250 zahraničních studentů. Fakulta je velmi oblíbená i díky zcela nadstandardním možnostem studijních pobytů a stáží na partnerských univerzitách v Evropě, Asii a v Americe. Zaměstnanci fakulty prosazují individuální a přátelský přístup ke studentům a posilují také kolektivní identitu. Ne nadarmo byla fakulta opakovaně zvolena Fakultou roku mezi veřejnými fakultami s ekonomickým zaměřením. Nedílnou aktivitou fakulty je výzkumná činnost a spolupráce s aplikovanou sférou. Absolventi FIM UHK nacházejí široké uplatnění v soukromých i veřejných institucích. Součástí fakulty je Institut dalšího vzdělávání, který pořádá řadu odborných kurzů a školení pro instituce, firmy i jednotlivce.";
    public static void main(String[] args) {
        Map<String, Integer> words = new WordAnalyzer().analyze(data);

        for (String string : words.keySet()) {
            System.out.println("[" + string + ": "+ words.get(string) + "]");
        }

        System.out.println("--------------------");

        Map<String, Integer> chars = new CharAnalyzer().analyze(data);

        for (String string : chars.keySet()) {
            System.out.println("[" + string + ": "+ chars.get(string) + "]");
        }
    }
}