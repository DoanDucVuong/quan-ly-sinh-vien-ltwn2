$entityDir = "d:\SVN3\LTW1\LTWN2\kthp_ltwn2\src\main\java\com\example\kthp_ltwn2\entity"
Get-ChildItem -Path $entityDir -Filter "*.java" | ForEach-Object {
    $content = Get-Content $_.FullName -Raw
    $content = $content -replace ', columnDefinition = "UNIQUEIDENTIFIER"', ''
    $content = $content -replace 'columnDefinition = "UNIQUEIDENTIFIER", ', ''
    $content = $content -replace 'columnDefinition = "UNIQUEIDENTIFIER"', ''
    Set-Content -Path $_.FullName -Value $content -NoNewline
    Write-Host "Fixed: $($_.Name)"
}
