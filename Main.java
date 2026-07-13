package task.date0715;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Wise Saying(명언) 클래스
class WS{
    int id;
    String content;
    String author;

    public WS(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public void setContent(String content) { this.content = content; }
    public void setAuthor(String author) { this.author = author; }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<WS> list = new ArrayList<>(); // 명언 저장 클래스 리스트
        String command = ""; // 명령 입력 변수
        int id = 0; // 명언 번호 변수

        System.out.println("== 명언 맵 ==");

        while (!command.equals("종료")) {
            System.out.print("명령) ");
            command = sc.nextLine();

            if(command.equals("등록")) {
                System.out.print("명언 : ");
                String content = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();

                list.add(new WS(++id, content, author));

                System.out.println(id + "번 명언이 등록되었습니다.");
            }
            else if(command.equals("목록")) {
                if(list.isEmpty()) {
                    System.out.println("목록이 비어있습니다.");
                    continue;
                }
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (WS ws : list.reversed()) {
                    System.out.println(ws.id + " / " + ws.author + " / " + ws.content);
                }
            }
            else if(command.contains("삭제")) {
                try {
                    // command의 "=" 다음 숫자 추출하여 int형 변환
                    int targetId = Integer.parseInt(command.substring(command.indexOf("=") + 1));

                    WS ws = getWS(targetId, list);

                    if(ws != null) {
                        list.remove(ws);
                        System.out.println(ws.id + "번 명언이 삭제되었습니다.");
                    } else {
                        System.out.println(targetId + "번 명언은 존재하지 않습니다.");
                    }

                } catch (Exception e) {
                    System.out.println("다시 시도해주세요. hint : 삭제?id={targetId} ");
                }
            }
            else if(command.contains("수정")) {
                try {
                    // command의 "=" 다음 숫자 추출하여 int형 변환
                    int targetId = Integer.parseInt(command.substring(command.indexOf("=") + 1));

                    WS ws = getWS(targetId, list);

                    if (ws != null) {
                        String input = "";
                        System.out.println("명언(기존) : " + ws.content);
                        System.out.print("명언 : ");
                        input = sc.nextLine();
                        ws.setContent(input);

                        System.out.println("작가(기존) : " + ws.author);
                        System.out.print("작가 : ");
                        input = sc.nextLine();
                        ws.setAuthor(input);
                    } else {
                        System.out.println(targetId + "번 명언은 존재하지 않습니다.");
                    }
                } catch (Exception e) {
                    System.out.println("다시 시도해주세요. hint : 수정?id={targetId} ");
                }
            }
        }
    }

    public static WS getWS(int targetId, List<WS> list) {

        // 순회하여 WSClass 찾아서 리턴
        for (WS ws : list) {
            if(ws.id == targetId) {
                return ws;
            }
        }

        return null; // 발견하지 못했을 때 null 리턴
    }
}
