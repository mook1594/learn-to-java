package modern.java.chapter3.ex1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ex1 {

    public static void main(String... args) throws Exception {
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    }

    // 실행 어라운드 패턴 (try-with-resources)
    public static String processFile() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return br.readLine();
        }
    }

    // 함수형 인터페이스 이용
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return p.process(br);
        }
    }


}
