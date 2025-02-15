    document.addEventListener("DOMContentLoaded", function() {
        const importeBruto = document.getElementById("importeBruto");
        const porcentImpuestos = document.getElementById("porcentImpuestos");
        const importeNeto = document.getElementById("importeNeto");

        function calcularImporteNeto() {
            let bruto = parseFloat(importeBruto.value) || 0;
            let impuestos = parseFloat(porcentImpuestos.value) || 0;
            let neto = bruto * (1 - impuestos / 100);
            importeNeto.value = neto.toFixed(2);
        }

        importeBruto.addEventListener("input", calcularImporteNeto);
        porcentImpuestos.addEventListener("input", calcularImporteNeto);
    });