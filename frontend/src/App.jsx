import { useState, useCallback } from 'react';
import { useDebouncedCallback } from 'use-debounce';
import PasswordInput from './components/PasswordInput';
import StrengthBar from './components/StrengthBar';
import SuggestionsList from './components/SuggestionsList';
import PasswordComparison from './components/PasswordComparison';
import { analyzePassword } from './services/passwordApi';
import PasswordGenerator from './components/PasswordGenerator';
import './App.css';

function App() {
  const [analysis, setAnalysis] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const debouncedAnalyze = useDebouncedCallback(async (password) => {
    if (!password) {
      setAnalysis(null);
      return;
    }

    setLoading(true);
    setError(null);

    try {
      const result = await analyzePassword(password);
      setAnalysis(result);
    } catch (err) {
      setError('No se pudo conectar con el servidor');
    } finally {
      setLoading(false);
    }
  }, 400);

  const handleAnalyze = useCallback((password) => {
    debouncedAnalyze(password);
  }, [debouncedAnalyze]);

  return (
    <div className="app">
      <h1>Analizador de Contraseñas</h1>
      <div className="card">
        <div className="password-field">
          <PasswordInput onAnalyze={handleAnalyze} />
        </div>

        {loading && <p className="status-text">Analizando...</p>}
        {error && <p className="error">{error}</p>}

        {analysis && (
          <>
            <StrengthBar level={analysis.strengthLevel} entropy={analysis.entropy} />
            <SuggestionsList suggestions={analysis.suggestions} />
          </>
        )}

        <PasswordComparison />
        <PasswordGenerator />
      </div>
    </div>
  );
}

export default App;