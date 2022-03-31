# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/#build-image)

#### Vérifier que les brockers sont bien démarrés
```
docker run --tty --network=host confluentinc/cp-kafkacat kafkacat -L -b localhost:19092
docker run --tty --network=host confluentinc/cp-kafkacat kafkacat -L -b localhost:29092
docker run --tty --network=host confluentinc/cp-kafkacat kafkacat -L -b localhost:39092
```

#### Consulter les messages reçues par les brokers
```
docker run --tty --network=host confluentinc/cp-kafkacat kafkacat -C -b localhost:19092 -t twitter-topic
docker run --tty --network=host confluentinc/cp-kafkacat kafkacat -C -b localhost:29092 -t twitter-topic
docker run --tty --network=host confluentinc/cp-kafkacat kafkacat -C -b localhost:39092 -t twitter-topic
```

#### Encrypter et décrypter un mot de passe en utilisant JCE
```
spring encrypt password-config --key config-key
spring decrypt encrypted-password-config --key config-key
```

#### Démarrage de minikube + docker avec la configuration nécessaire ####
```
brew install minikube
brew install docker
brew install docker-compose
brew install cask virtualbox
minikube start --memory 8192 --cpus 2
minikube start --driver=virtualbox --memory 8192 --cpus 2

#documentation tiré de http://tdongsi.github.io/blog/2018/12/31/minikube-in-corporate-vpn/

#configuration de virtual box
VBoxManage controlvm minikube natpf1 k8s-apiserver,tcp,127.0.0.1,8443,,8443
VBoxManage controlvm minikube natpf1 k8s-dashboard,tcp,127.0.0.1,30000,,30000
VBoxManage controlvm minikube natpf1 jenkins,tcp,127.0.0.1,30080,,30080
VBoxManage controlvm minikube natpf1 docker,tcp,127.0.0.1,2376,,2376

#contexte pour le vpn
kubectl config set-cluster minikube-vpn --server=https://127.0.0.1:8443 --insecure-skip-tls-verify
kubectl config set-context minikube-vpn --cluster=minikube-vpn --user=minikube

#swith ver le context
kubectl config use-context minikube-vpn

# mise à jour variable environnement
eval $(minikube docker-env)
export DOCKER_HOST="tcp://127.0.0.1:2376"
alias dockervpn="docker --tlsverify=false"

# utilisation du contexte hors vpn
kubectl config use-context minikube

```
