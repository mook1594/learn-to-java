##### [GO TO LIST](../README.md)

# 아이템9. try-finally 보다는 try-with-resources를 사용하라

### try-finally
- 전통적으로 많이 쓰이는 해제를 보장하는 수단
```java
public static String firstLineOfFile(String path) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(path));
    try{
        return br.readLine();
    } finally {
        br.close();
    }
}
```
- 만약 자원을 하나더 사용한다면?
```java
public static void copy(String src, String dst) throws IOException {
    InputStream in = new FileInputStream(src);
    try {
        OutputStream out = new FileOuputStream(dst);
        try {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n); 
            }
        } finally {
            out.close();
        }
    } finally {
        in.close();
    }
}
```

### try-with-resources
```java
public static String firstLineOfFile(String path) throws IOException {
    try (BufferedReader br = new BufferedReader (new FileReader(path))) {
        return br.readLine();
    }
}
```
- 복수 자원이라면?
```java
public static void copy(String src, String dst) throws IOException {
    try (InputStream in = new FileInputStream(src); 
            OutputStream out = new FileOutputStream(dst)) {
        byte[] buf = new byte[BUFFER_SIZE];
        int n;
        while ((n = in.read(buf)) >= 0) {
            out.write(buf, 0, n);
        }
    }
}
```

### try-with-resources, catch
```java
public static String firstLineOfFile(String path, String defaultVal) {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        return br.readLine();
    } catch (IOException e) {
        return defaultVal;
    }
}
```

> 꼭 회수 해야하는 것은 try-with-resources를 사용하자. 정확하고 쉽게 자원을 회수 할 수 있다.
