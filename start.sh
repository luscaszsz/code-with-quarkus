echo "🔨 Buildando módulos..."

cd investimento-app
../mvnw clean package -DskipTests
cd ../investimento-jwt
../mvnw clean package -DskipTests
cd ..

echo "🐳 Subindo containers..."
docker compose down
docker compose up --build