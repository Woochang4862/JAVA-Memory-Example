//PC Register 값은 현재 실행중인 JVM의 주소가 들어가있
public class Example {

    public static String str_by_instance=new String("Hello World!"); //Method (Static) Area 에 생성

    public static void main(String[] args) {
        //실행을 위해 Main Thread, Stack Area 에 Push 됨
        Thread mainT = Thread.currentThread();
        System.out.println("main thread's name: "+mainT.getName());

        // 동작 방식에 대한 이해가 필요하다.
        // String을 리터럴로 선언할 경우 내부적으로 String의 intern() 메서드가 호출되게 된다.
        // intern() 메서드는 주어진 문자열이 string constant pool에 존재하는지 검색하고 있다면
        // 그 주소값을 반환하고 없다면 string constant pool에 넣고 새로운 주소값을 반환하게 된다.
        String str_by_literal="Hello World!";
        System.out.println("address of str_by_literal's literal value : "+str_by_literal.intern());
        //P는 Main Thread, Stack Area 에 Push 됨
        P(str_by_literal);
        //P는 Main Thread, Stack Area 에서 Pop 됨

        //P는 Hello Thread, Stack Area 에 Push 됨
        //P는 Hello Thread, Stack Area 에서 Pop 됨
        new Hello().start();

        //종료 위해 Main Thread, Stack Area 에서 Pop 됨
    }

    //Method (Static) Area 에 생성
    public static void P(String str){
        System.out.println(str+Thread.currentThread());
    }
}

class Hello extends Thread
{
    @Override
    public void run()
    {
        this.setName("Hello");
        Example.P(Example.str_by_instance);
    }
}
