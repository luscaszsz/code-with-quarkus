#./generate_jwtKeys.sh
#cd investimento-app
#../mvnw clean compile package
#cd ../investimento-jwt
#../mvnw clean compile package
#cd ../
#docker compose up

#
#./mvnw clean package
#docker compose up

echo "🔨 Buildando módulos..."

cd investimento-app
../mvnw clean package -DskipTests
cd ../investimento-jwt
../mvnw clean package -DskipTests
cd ..

echo "🐳 Subindo containers..."
docker compose down
docker compose up --build