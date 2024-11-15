# 터미널에서 http 요청 

인텔리제이에서 Alt+F12를 누르면 터미널이 열린다.

```bash
http -v <https://open.er-api.com/v6/latest/USD>
```

위 명령하면 json형식의 데이터와 내가 보낸 요청등 여러 정보를 볼 수 있다.

- code

  ```java
  // 환율 가져오기
  // <https://open.er-api.com/v6/latest/USD>
  // Alt+F12 -> http -v "URL입력"
  URL url = new URL("<https://open.er-api.com/v6/latest/>" + currency);
  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
  BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
  String response = br.lines().collect(Collectors.joining());
  ```