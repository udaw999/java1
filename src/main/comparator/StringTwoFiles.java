package comparator;

public class StringTwoFiles {
    private static int count = 1;
    private int numStr;//номер строки
    private String str;//строка файла
    private boolean belongsFile_1;//принадлежит ли файлу 1
    private boolean belongsFile_2;// принадлежит ли файлу 2
    private int numBlok;// номер блока

    StringTwoFiles(String str, boolean belongsFile_1, boolean belongsFile_2, int numBlok){
        this.numStr = count++;
        this.str = str;
        this.belongsFile_1 = belongsFile_1;
        this.belongsFile_2 = belongsFile_2;
        this.numBlok = numBlok;
    }

    @Override
    public String toString() {
        return "comparator.StringTwoFiles{" +
                ", numStr=" + numStr +
                ", str='" + str + '\'' +
                ", belongsFile_1=" + belongsFile_1 +
                ", belongsFile_2=" + belongsFile_2 +
                ", numBlok=" + numBlok +
                '}';
    }

    public int getNumStr() {
        return numStr;
    }

    public String getStr() {
        return str;
    }

    public boolean isBelongsFile_1() {
        return belongsFile_1;
    }

    public boolean isBelongsFile_2() {
        return belongsFile_2;
    }

    public int getNumBlok() {
        return numBlok;
    }
}

