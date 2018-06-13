Todos os códigos foram testados em um terminal Linux com java 1.8.0, não há garantias de que eles funcionam em outros ambientes.

### Servidor
#### Compilação
Na pasta com os códigos-fonte do servidor, digitar:

javac *.java

#### Execução
Na pasta com os arquivos .class do servidor (gerados na etapa de compilação), digitar:

java -classpath < caminho até o diretório com os arquivos .class do servidor > -Djava.rmi.server.hostname=< ip da máquina em que o servidor será executado > -Djava.rmi.server.codebase = < caminho até o diretório com os arquivos .class do servidor > -Djava.rmi.security.policy=server.policy Server

##### Exemplo
hboschirolli@hboschirolli-XPS-L421X:~/unicamp/2018.1/mc823/project3/MC833-Project3/src$ javac *.java
hboschirolli@hboschirolli-XPS-L421X:~/unicamp/2018.1/mc823/project3/MC833-Project3/src$ java -classpath /home/hboschirolli/unicamp/2018.1/mc823/project3/MC833-Project3/src/ -Djava.rmi.server.hostname=192.168.1.105 -Djava.rmi.server.codebase=/home/hboschirolli/unicamp/2018.1/mc823/project3/MC833-Project3/src/ -Djava.security.policy=server.policy Server


### Cliente
#### Compilação
Na pasta com os códigos-fonte do cliente, digitar:

javac *.java

#### Execução
Na pasta com os arquivos .class do cliente (gerados na etapa de compilação), digitar:

java -classpath < caminho até o diretório com os arquivos .class do cliente > -Djava.security.policy=client.policy < ip da máquina em que o servidor está rodando >

##### Exemplo
hboschirolli:~/unicamp/current/mc833/project3/MC833-Project3/src $ javac *.java
hboschirolli:~/unicamp/current/mc833/project3/MC833-Project3/src $ java -classpath /home/hboschirolli/unicamp/current/mc833/project3/MC833-Project3/src -Djava.security.policy=client.policy Client 192.168.1.105
