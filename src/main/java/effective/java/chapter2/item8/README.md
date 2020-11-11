##### [GO TO LIST](../README.md)

# 아이템 8. finalizer와 cleaner 사용을 피하라 

### finalizer와 cleaner의 단점
- 실행되는데 까지 오래걸린다 (Thread 할당에 우선순위가 낮음)
- 해제 되기만을 기다라다가 해제 되지않아 OutOfMemoryError가 발생 할 수 있다.
- 성능 문제를 동반한다. (try-with-resources로 해제시 12ns, finalizer 550ns)
- 보안 문제를 일으킬 수 있다. (직렬화에서 예외가 발생시 만들다 만 객체로 다음 동작을 수행한다.) => 이를 방어하기위해 final로 선언

### 그렇다면 뭘써야될까?
- AutoCloseable을 구현하고 해제시 close를 호출
- try-with-resources 사용 [아이템9 참조](../item9/README.md)

### 그럼 finalizer와 cleaner는 왜 있는거지?
- close 메서드를 호출 하지않을때 안전장치 (하지않는것보단 느려도 하는것이 나으니까)
- 네이티브 메서드를 통해서 위임한 네이티브 객체를 해제할때 (GC는 자바객체가 아니기에 그 존재를 알지 못함)
- [cleaner의 사용 예제](./Room.java)

> cleaner는 안전망 역할이나 중요하지 않은 네이티브 자원회수용으로만 사용.  
> 불확실성과 성능 저하에 주의해야한다.
