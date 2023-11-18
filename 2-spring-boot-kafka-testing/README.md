



```bash
$ docker ps
CONTAINER ID   IMAGE                          COMMAND                  CREATED         STATUS         PORTS                                                        NAMES
4539dbdc8440   confluentinc/cp-kafka:latest   "sh -c 'while [ ! -f…"   4 minutes ago   Up 4 minutes   9092/tcp, 0.0.0.0:57323->2181/tcp, 0.0.0.0:57324->9093/tcp   pensive_knuth
2cc7ebb842b6   postgres:latest                "docker-entrypoint.s…"   5 minutes ago   Up 5 minutes   0.0.0.0:57275->5432/tcp                                      beautiful_albattani
3daca18b7129   testcontainers/ryuk:0.5.1      "/bin/ryuk"              5 minutes ago   Up 5 minutes   0.0.0.0:57234->8080/tcp                                      testcontainers-ryuk-edcee0a1-1052-4ddd-bf28-01a876243b8e
```

```bash
$ kafka-topics --bootstrap-server localhost:57324 --list

```