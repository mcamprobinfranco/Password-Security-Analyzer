import { useState } from 'react';
import { useDebouncedCallback } from 'use-debounce';
import { comparePasswords } from '../services/passwordApi';
import StrengthBar from './StrengthBar';

function PasswordComparison() {
  const [passwordA, setPasswordA] = useState('');
  const [passwordB, setPasswordB] = useState('');
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);

  const debouncedCompare = useDebouncedCallback(async (a, b) => {
    if (!a || !b) {
      setResult(null);
      return;
    }

    setError(null);
    try {
      const data = await comparePasswords(a, b);
      setResult(data);
    } catch (err) {
      setError('No se pudo comparar las contraseñas');
    }
  }, 400);

  const handleChangeA = (e) => {
    const value = e.target.value;
    setPasswordA(value);
    debouncedCompare(value, passwordB);
  };

  const handleChangeB = (e) => {
    const value = e.target.value;
    setPasswordB(value);
    debouncedCompare(passwordA, value);
  };

  return (
    <div className="comparison">
      <h2>Comparar contraseñas</h2>

      <div className="comparison-grid">
        <div className="password-field">
          <label htmlFor="passwordA">Contraseña A</label>
          <input
            id="passwordA"
            type="text"
            value={passwordA}
            onChange={handleChangeA}
            placeholder="Primera contraseña..."
          />
        </div>

        <div className="password-field">
          <label htmlFor="passwordB">Contraseña B</label>
          <input
            id="passwordB"
            type="text"
            value={passwordB}
            onChange={handleChangeB}
            placeholder="Segunda contraseña..."
          />
        </div>
      </div>

      {error && <p className="error">{error}</p>}

      {result && (
        <div className="comparison-results">
          <div className={`comparison-column ${result.strongerPassword === 'A' ? 'winner' : ''}`}>
            <StrengthBar level={result.analysisA.strengthLevel} entropy={result.analysisA.entropy} />
          </div>
          <div className={`comparison-column ${result.strongerPassword === 'B' ? 'winner' : ''}`}>
            <StrengthBar level={result.analysisB.strengthLevel} entropy={result.analysisB.entropy} />
          </div>
        </div>
      )}

      {result && (
        <p className="comparison-verdict">
          {result.strongerPassword === 'TIE'
            ? 'Ambas contraseñas tienen fortaleza similar'
            : `La contraseña ${result.strongerPassword} es más fuerte`}
        </p>
      )}
    </div>
  );
}

export default PasswordComparison;