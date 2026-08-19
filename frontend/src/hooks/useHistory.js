import { useState, useEffect } from 'react';

const STORAGE_KEY = 'password-analyzer-history';
const MAX_ENTRIES = 10;

export function useHistory() {
  const [history, setHistory] = useState(() => {
    const saved = localStorage.getItem(STORAGE_KEY);
    return saved ? JSON.parse(saved) : [];
  });

  useEffect(() => {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(history));
  }, [history]);

  const addEntry = (analysis, source) => {
    const entry = {
      id: crypto.randomUUID(),
      timestamp: new Date().toISOString(),
      source, // "analyzed" o "generated"
      length: analysis.length,
      strengthLevel: analysis.strengthLevel,
      entropy: analysis.entropy,
    };

    setHistory((prev) => [entry, ...prev].slice(0, MAX_ENTRIES));
  };

  const removeEntry = (id) => {
    setHistory((prev) => prev.filter((entry) => entry.id !== id));
  };

  const clearHistory = () => {
    setHistory([]);
  };

  return { history, addEntry, removeEntry, clearHistory };
}