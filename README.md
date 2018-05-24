# sd-concorrencia

Atividade extra de concorrência da cadeira de Sistemas Distribuídos

# How to
1: Rode o *install.sh* para gerar o *.jar* de cada projeto
```
sh ./install.sh
```
2: Levante os *containers*
```
docker-compose up -d
```
3: Visualize o *logs* de cada *container*/exemplo
```
docker-compose logs sync-buffer
```
```
docker-compose logs circular-buffer
```
```
docker-compose logs blocking-buffer
```
