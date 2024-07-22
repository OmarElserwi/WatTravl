# Navigate to the src/main/kotlin directory
Write-Output "Navigating to the src/main/kotlin directory..."
Set-Location -Path (Join-Path -Path (Split-Path -Parent $MyInvocation.MyCommand.Definition) -ChildPath "src/main/kotlin")
Write-Output "Current directory: $(Get-Location)"

# Set the source directory
$SRC_DIR = "com/example/model/MC"

# List of all Kotlin files to compile
$FILES = @(
    "Dijkstra.kt"
    "main.kt"
    "NodeStructure.kt"
    "floor1.kt"
    "floor2.kt"
    "floor3.kt"
    "floor4.kt"
    "floor5.kt"
    "floor6.kt"
)

# Compile all the Kotlin files
Write-Output "Compiling Kotlin files..."
$FILES_PATHS = $FILES | ForEach-Object { Join-Path -Path $SRC_DIR -ChildPath $_ }
kotlinc @FILES_PATHS -include-runtime -d "../../../../build/classes/kotlin/main.jar"

if ($LASTEXITCODE -eq 0) {
    Write-Output "Compilation successful."
    Write-Output "Running the application..."
    # Run the application
    kotlin -classpath "../../../../build/classes/kotlin/main.jar" com.example.model.MC.MainKt
} else {
    Write-Output "Compilation failed."
    exit 1
}
