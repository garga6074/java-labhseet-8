# Lab Sheet 08 — Object Oriented Programming Using Java

**MCA III Semester | Last Date: 26-10-2026**

This repository includes runnable Java source-code solutions for all 20 exercises covering JavaBeans, serialization, property-change events, introspection, `InetAddress`, TCP, UDP, `URL` and `URLConnection`.

## Requirements
- JDK 17 or later
- VS Code / IntelliJ IDEA / Eclipse / Command Prompt
- Internet connection for exercises 18 and 19

## Project structure
```text
Lab_Sheet_08_Java_OOP_Networking/
├── src/main/java/lab08/
│   ├── beans/
│   └── network/
│       ├── tcp/
│       ├── udp/
│       └── web/
├── compile.bat
├── compile.sh
├── run-example.bat
├── EXPECTED_OUTPUTS.md
├── README.md
└── .gitignore
```

## Compile all programs

Windows:
```bat
compile.bat
```

macOS / Linux:
```bash
chmod +x compile.sh
./compile.sh
```

The compiled classes are saved to `out/`.

## Run JavaBean exercises (1–10)

```bash
java -cp out lab08.beans.BeanExercisesDemo
```

This single demonstration runs StudentBean, RectangleBean, Serializable EmployeeBean, DateBean, UserBean, AccountBean property-change events, StudentBean introspection, CarBean and CircleBean.

## Run Exercise 11

```bash
java -cp out lab08.network.InetAddressDemo
```

## Exercises 12–14: TCP Hello Server and Client

Open two terminals in the project root.

Terminal 1:
```bash
java -cp out lab08.network.tcp.HelloServer
```

Terminal 2:
```bash
java -cp out lab08.network.tcp.HelloClient
```

Expected server output:
```text
HelloServer listening on port 5000...
Client connected
Received from client: Hello Server
```

The same updated `HelloServer` demonstrates Exercise 12 (accepting a connection) and Exercise 14 (reading and printing the client's message). It accepts one client and exits.

## Exercise 15: TCP Echo Server

Terminal 1:
```bash
java -cp out lab08.network.tcp.EchoServer
```

Terminal 2:
```bash
java -cp out lab08.network.tcp.EchoClient
```

Type a line in the client. The server sends the same line back.

## Exercises 16–17: UDP Sender and Receiver

Terminal 1:
```bash
java -cp out lab08.network.udp.MessageReceiver
```

Terminal 2:
```bash
java -cp out lab08.network.udp.MessageSender
```

The sender transmits `Ping` to `127.0.0.1:6000`. For another computer, pass the server IP and port:
```bash
java -cp out lab08.network.udp.MessageSender 192.168.1.10 6000
```

## Exercises 18–19: WebReader and FileDownloader

```bash
java -cp out lab08.network.web.WebReader
java -cp out lab08.network.web.FileDownloader
```

Both require internet connectivity. The FileDownloader uses the exact URL in the assignment, `https://www.example.com/sample.txt`. That path may not host a real file; a server-side HTTP 404 is a resource issue, not a Java compilation error. For a successful download demonstration, replace the address with a valid downloadable text-file URL.

## Exercise 20: Multithreaded TCP Server

Terminal 1:
```bash
java -cp out lab08.network.tcp.MultiClientServer
```

Run client commands from separate terminals:
```bash
java -cp out lab08.network.tcp.MultiClientClient 127.0.0.1 5002 First-client
java -cp out lab08.network.tcp.MultiClientClient 127.0.0.1 5002 Second-client
```

The server uses a cached thread pool and prints the message with the worker thread's name.

## Exercise-to-source mapping

| No. | Exercise | Source |
|---:|---|---|
| 1 | StudentBean with id, name, grade | `beans/StudentBean.java` |
| 2 | RectangleBean area/perimeter | `beans/RectangleBean.java` |
| 3 | Serializable EmployeeBean | `beans/EmployeeBean.java` |
| 4 | Read-only currentDate | `beans/DateBean.java` |
| 5 | Write-only password | `beans/UserBean.java` |
| 6–7 | AccountBean listener and event | `beans/AccountBean.java` |
| 8 | Introspector properties and methods | `beans/BeanExercisesDemo.java` |
| 9 | CarBean set/get | `beans/CarBean.java` |
| 10 | CircleBean derived properties | `beans/CircleBean.java` |
| 11 | IP address and host name | `network/InetAddressDemo.java` |
| 12–14 | TCP Hello server/client | `network/tcp/HelloServer.java`, `HelloClient.java` |
| 15 | TCP echo server | `network/tcp/EchoServer.java`, `EchoClient.java` |
| 16–17 | UDP message sender/receiver | `network/udp/MessageSender.java`, `MessageReceiver.java` |
| 18 | Read web page | `network/web/WebReader.java` |
| 19 | Download with URLConnection | `network/web/FileDownloader.java` |
| 20 | Multithreaded TCP server | `network/tcp/MultiClientServer.java`, `MultiClientClient.java` |

## Implementation notes
- JavaBeans use private fields, public no-argument constructors and standard getter/setter names.
- `DateBean.currentDate`, `CircleBean.area` and `CircleBean.circumference` are read-only properties: getter only, no setter.
- `UserBean.password` is write-only: setter only; the demo never prints the password.
- `AccountBean` uses `PropertyChangeSupport`.
- `EmployeeBean` implements `Serializable` and declares `serialVersionUID`.
- Network examples use UTF-8 and try-with-resources to close sockets/streams.

## GitHub upload
1. Extract the ZIP.
2. Create a GitHub repository named `MCA-Lab-Sheet-08-Java`.
3. Upload the contents of the extracted folder.
4. Commit the files and README.
5. Keep generated IDE folders and compiled classes out of the repository unless required by your instructor.
