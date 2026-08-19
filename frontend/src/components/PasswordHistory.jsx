const LEVEL_LABELS = {
  WEAK: 'Débil',
  MEDIUM: 'Media',
  STRONG: 'Fuerte',
  VERY_STRONG: 'Muy fuerte',
};

const SOURCE_LABELS = {
  analyzed: 'Analizada',
  generated: 'Generada',
  compared: 'Comparada',
};

function PasswordHistory({ history, onRemove, onClear }) {
  if (history.length === 0) {
    return null;
  }

  return (
    <div className="history">
      <div className="history-header">
        <h2>Historial</h2>
        <button type="button" className="clear-btn" onClick={onClear}>
          Vaciar
        </button>
      </div>

      <ul className="history-list">
        {history.map((entry) => (
          <li key={entry.id} className={`history-item level-${entry.strengthLevel.toLowerCase()}`}>
            <div className="history-info">
              <span className="history-badge">{SOURCE_LABELS[entry.source]}</span>
              <span>{entry.length} caracteres</span>
              <span>{LEVEL_LABELS[entry.strengthLevel]}</span>
              <span className="history-entropy">{entry.entropy.toFixed(1)} bits</span>
            </div>
            <button type="button" className="history-remove" onClick={() => onRemove(entry.id)}>
              ✕
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default PasswordHistory;