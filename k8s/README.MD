minikube start
kubectl get pods
kubectl apply -f mysql-user-config.yaml
kubectl apply -f mysql-user-secret.yaml
kubectl apply -f mysql-user.yaml
kubectl apply -f user.yaml
kubectl apply -f auth.yaml

kubectl get all


kubectl get svc # get services
kubectl get node    
minkube ip # minikube create juste 1 node, so we get node ip and we can access to our NodePort services
kubectl describe pod <pod-name>


Pod log
kubectl logs podname

Url service
minikube service name-service --url


# https://stackoverflow.com/questions/36874880/kubernetes-cannot-pull-local-image
To use local image, create docker local registry
docker run -d -p 5000:5000 --restart=always --name registry registry:2.7
docker tag <local-image-repository>:<local-image-tag> localhost:5000/<local-image-name>
docker push localhost:5000/<local-image-name>