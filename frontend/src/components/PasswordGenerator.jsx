import { useState } from 'react';
import { generatePassword } from '../services/passwordApi';

function PasswordGenerator() {
  const [length, setLength] = useState(16);
  const [options, setOptions] = useState({
    includeUppercase: true,
    includeLowercase: true,
    includeNumbers: true,
    includeSymbols: true,
  });
  const [generated, setGenerated] = useState('');
  const [copied, setCopied] = useState(false);
  const [error, setError] = useState(null);

  const toggleOption = (key) => {
    setOptions((prev) => ({ ...prev, [key]: !prev[key] }));
  };

  const handleGenerate = async () => {
    setError(null);
    setCopied(false);
    try {
      const result = await generatePassword({ length, ...options });
      setGenerated(result.password);
    } catch (err) {
      setError('No se pudo generar la contraseña');
    }
  };

  const handleCopy = async () => {
    if (!generated) return;
    await navigator.clipboard.writeText(generated);
    setCopied(true);
    setTimeout(() => setCopied(false), 1500);
  };

  return (
    <div className="generator">
      <h2>Generar contraseña segura</h2>

      <div className="generator-length">
        <label htmlFor="length">Longitud: {length}</label>
        <input
          id="length"
          type="range"
          min="4"
          max="64"
          value={length}
          onChange={(e) => setLength(Number(e.target.value))}
        />
      </div>

      <div className="generator-options">
        <label>
          <input
            type="checkbox"
            checked={options.includeUppercase}
            onChange={() => toggleOption('includeUppercase')}
          />
          Mayúsculas
        </label>
        <label>
          <input
            type="checkbox"
            checked={options.includeLowercase}
            onChange={() => toggleOption('includeLowercase')}
          />
          Minúsculas
        </label>
        <label>
          <input
            type="checkbox"
            checked={options.includeNumbers}
            onChange={() => toggleOption('includeNumbers')}
          />
          Números
        </label>
        <label>
          <input
            type="checkbox"
            checked={options.includeSymbols}
            onChange={() => toggleOption('includeSymbols')}
          />
          Símbolos
        </label>
      </div>

      <button type="button" className="generate-btn" onClick={handleGenerate}>
        Generar
      </button>

      {error && <p className="error">{error}</p>}

      {generated && (
        <div className="generated-result">
          <code>{generated}</code>
          <button type="button" onClick={handleCopy}>
            {copied ? '✓ Copiado' : 'Copiar'}
          </button>
        </div>
      )}
    </div>
  );
}

export default PasswordGenerator;