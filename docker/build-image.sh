cp ../target/livibank-ans-1.0.0-SNAPSHOT.jar .
cp ../target/classes/liviBankAns.yaml ./config/.
docker build -t livibank-ans .