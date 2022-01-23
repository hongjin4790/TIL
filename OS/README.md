# Operating System
- OS: 하드웨어를 컨트롤, 관리하는 소프트웨어
- Kernel
    - 항상 수행해야되는 하나의 프로그램

## Real-time System
### hard real-time system
- 데드라인을 잘 지켜야한다. 그렇지 않으면 치명적인 손상을 입는다
- EX) 원자로 제어 시스템, 비행 제어 시스템

### Soft real-time system
- 데드라인을 못지켜도 치명적인 손상을 입지 않는다
- Ex) 스트리밍 서비스, 컴퓨터 게임

![image](https://user-images.githubusercontent.com/29851704/148646100-a4d06679-9823-406b-b3d3-a01933ff6d76.png)

- System bus : 시스템들을 연결하는 애들
- master : 통신을 주는 시스템
- slave : 통신을 받는 시스템(메모리는 보통 slave임)
- interrupt : 뭔가 오면 알림을 주면 그때수행

## Process Conccept
- Process: 프로그램을 실행시키는 주체
- Program: 파일시스템에 존재하는 실행파일
- 프로그램은 수행해야 할 정렬 된 작업의 명확한 그룹이다. 반면에 실행중인 프로그램의 인스턴스는 프로세스이다.

- Multiprocessing : 프로세서를 여러개 두어서 수행시키는 것 -> 물리적으로 cpu가 여러개있다.
- Multitasking : Time-Shared -> processing들이 동시에 수행되는 것처럼 보이게 하는 것

![image](https://user-images.githubusercontent.com/29851704/148646654-1b35276f-dd62-48e4-878e-e2c6651ce7cb.png)

- text : 코드의 구조 ex) 명령어, 어셈블러, 기계어
- data : 원래필요한 변수, 전역변수, 정적변수 ex) static, 각종 상수
- heap : 프로그램을 수행하면서 만들어지는 변수, 동적변수 ex) new, malloc...
- stack : 함수호출 또는 일시적으로 생성되었다가 사라지는 파라미터, 매개변수, 리턴 벨류, 지역변수 등..

## Process State

![image](https://user-images.githubusercontent.com/29851704/148646675-7aa88694-3ccf-497b-979b-13632a1d824a.png)

- New : 프로세스가 만들어질 때
- Ready : 수행할 준비가 되어있지만 다른 프로세스가 cpu를 잡아먹기 때문에 기다리는 것
- Waiting : 특정한 상황을 기다리고 있고, 입력이 되면 ready로 바뀐다.
- Running : 프로세스가 동작하는 것
- Terminated : 프로세스가 다 끝난상태
 
 <b> ready, waiting : cpu를 먹고있는 상태가 아님</b>
 
 ## Context Switch
 
 ![image](https://user-images.githubusercontent.com/29851704/148646956-eb6f65c7-ed7c-4c1f-9050-e7b4654fefe3.png)

- Context Switch는 Switch(interrupt, system call)를 하게 되면 실행하고있던 Process P0를 끝내는게 아니라 잠시 멈춘다. 그러므로 P0가 쓰던 register 값(pc)을 저장을 하고, Process P1의 저장되었었던 state를 Load하는 과정이다.
- Context Switch은 하드웨어 단에서는 꽤 시간이 걸리는 작업이다. 그러므로 Context Switch을 자주하면 시간이 비례해서 걸린다.

## Process Creation
- 모든 프로세스는 제일 처음 생성된 프로세스를 제외하곤 프로세스가 생성을 해야한다.
- 모든 프로세스는 자신만의 아이디를 갖는다.

![image](https://user-images.githubusercontent.com/29851704/148647096-58a4cac4-c599-4d9e-963b-2a5366583fa9.png)


- fork(): 새로운 프로세스 생성
- exec() : 프로세스는 프로세스가 생성해야한다. 근데 완전히 다른 일을 수행하는 프로세스를 만들고 싶을때는 exec()를 사용하여 이전 코드를 싹 없애면 된다.
- wait() : 프로세스가 끝나면 Terminate 상태로 가도 잠시 대기한다. 나를 생성했던 부모 프로세스가 내 상태를 확인할 때까지(자식 프로세스의 리턴 값), 대기한다. 부모프로세스에서 wait()을 걸면, 계속 리턴값을 기다린다.
- 부모 프로세스에서 wait()를 안 넣어줘서 Terminated된 자식 프로세스가 wait()를 받지 못해 아무것도 수행하지 않는 “좀비 프로세스”가 만들어진다.


## Interprocess communication(IPC)
![image](https://user-images.githubusercontent.com/29851704/148647151-5807bee0-520f-4acc-b83d-65abcd80835b.png)

- Shared memory: 메모리 공간을 공유해주는 방법
     - Kernel에 도움 요청을 해야해서 제약사항이 많음.
 - Message passing: 일반적으로 많이 씀.
    - 특정 공간에 데이터를 쓴 다음 알람을 줘서 데이터가 필요한 	애들이 가져가는 방식 (보통은 계속 데이터를 기다리고 있다.)
    - 프로세스가 여러개 물렸을 때는 장점이다.

## Pipe

- pipe: 프로세스가 통신할 수 있는 통로 역할
- Ordinary pipes: 프로세스 생성할 때, 미리 꽂아 놓는 형식
- Producer - Consumer 구성에서 빈번하게 사용, unidirectional이다

- Ordinary pipe의 한계점: 부모자식관계가 아니면 쓸 수가 없다

## Thread
- 하나의 프로세스는 하나 이상의 Thread를 갖는다
- 프로세스를 복사하면 code, data, register등 다 복사된다.
- 하나의 프로그램에 다수의 Thread는 code 공간을 공유한다. 따라서 하나의 Thread에서 문제가 발생하면 OS가 code 공간에 할당된 resource를 회수하게 되므로  모든 Thread들이 멈추게 된다.
- 응답 속도가 빠르다. Process보다 Context Switching이 빠르다.

## Amdahl's law
- 컴퓨터 시스템의 일부를 개선할 때 전체적으로 얼마만큼의 최대 성능 향상이 있는지 계산하는 데 사용된다.

![image](https://user-images.githubusercontent.com/29851704/149663134-9b255939-070c-490f-aa40-10aa9a74a19b.png)
- S는 직력 부분
- N은 코어개수 

![image](https://user-images.githubusercontent.com/29851704/149663057-3598e6f4-45af-4802-bc06-272ea909e3c1.png)

## CPU Scheduling
- 어떠한 cpu가 어떤 프로세스를 쓸지 결정하는 것
- cpu를 최대한 노는기간 없게 프로세스들을 잘 관리
- cpu burst: 실제 프로세스가 cpu를 사용하는 시간
- I/O burst: waiting하는 기간

- Nonpreemtive: 한번 프로세스가 cpu를 장학하면 그 프로세스가 cpu를 안 쓴다는 신호를 보내기 전까지 아무도 cpu를 뺏어오지 못한다. 뺏는 경우는 스스로 놓거나, 시간이 다 되었을경우뿐
- Preemtive: 수행하는 중간에도 외부에서 강제로 ready queue로 보낼 수 있다.

- Dispatcher: Context Switching 할 때, 프로세스의 정보를 저장했다가 다른 프로세스 상태를 읽는 동작을 해주는 것 Context Switching을 너무 자주하면 프로세스가 실행하는 시간보다 Dispatcher수행시간이 더 길어진다.

- Throughput : 전체 프로세스가 끝나는데 걸리는 시간
- CPU utilization : CPU가 얼마나 놀지 않고 있었냐
- Turnaround time : 각각의 프로세스들이 시작하면서부터 끝날 때까지 걸리는 시간의 평균
- Response time : 사람과 상호작용을 안 해도 되는 일은 그냥 빨리 끝내기만하면 된다. 하지만 사람과 상호작용을 통해 해야하는일은 위의 것이 중요한 게 아니라 빨리 respon를 주는 것이 더 중요하다.

- waiting time : 각 프로세스마다 평균 waiting time들 지표로 삼겠다.
### First-Come, First-Served(FCFS):

![image](https://user-images.githubusercontent.com/29851704/150680762-088ff6d5-7a21-4b58-8ed6-9b528644bb15.png)

- 먼저 들어온 것부터 수행한다.
- Convoy effect: 하나의 프로세스가 앞에서 막아버리면 뒤에 프로세스가 사용 못한다

### Shortest-Job-First(SJF)
![image](https://user-images.githubusercontent.com/29851704/150680812-2ffa1970-65c5-4072-b4ff-cde9a27273e3.png)

- 제일 빨리 끝날만 한(Burst time) 프로세스를 먼저 선택하는 것
- 실제로는 Burst time을 알 수 있는 방법을 몰라서 못쓴다

### Round Robin(RR)
![image](https://user-images.githubusercontent.com/29851704/150680877-b0bc974d-b02b-4fb0-a328-6cb8c96b8918.png)

- time slice와 Preemtive성질이 있다. (위 예제는 q=4 이다)
- 하나의 프로세스는 한 번에 일정한 시간 이상을 수행할 수 없고 순서는 FCFS 이다.
- q가 작으면 작을수록 좋아 보이지만 context switching이 자주발생해서 overhead가 더 발생한다.(Dispatch latency)

### Priority Scheduling
![image](https://user-images.githubusercontent.com/29851704/150680968-a2279247-47ef-4720-b3f9-e765f47df7cc.png)


- 프로세스의 우선순위를 우선으로 한다.
- Priority가 낮을수록 높은 것이다.

### Starvation
- Priority가 낮아서 계속 돌아가는 프로세스 뒤로 걸려서 영구적으로 못 돌아가는 현상
- Priority를 아무리 낮게 놔두더라도 일정 시간마다 Priority를 계속 올려줘서 Starvation을 해결함

