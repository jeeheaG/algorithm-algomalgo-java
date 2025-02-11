package programmers;

/**
정해진 순서로 들어오면 햄버거 포장
몇개 포장 가능?

스택에 하나씩 넣음
넣을때마다 끝에서부터 확인
맞으면 pop
하나씩 넣음
*/

class pg_햄버거_만들기 {
    public int solution(int[] ingredient) {
        
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int ing : ingredient) {
            sb.append(ing);
            
            int len = sb.length();
            if (4 <= len && sb.substring(len-4).equals("1231")) {
                sb.delete(len-4, len);
                cnt++;
            }
        }
        
        return cnt;
    }
}
