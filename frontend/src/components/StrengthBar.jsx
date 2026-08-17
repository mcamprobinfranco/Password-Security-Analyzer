const LEVEL_CONFIG = {
  WEAK: { label: 'Débil', color: '#e74c3c', width: '25%' },
  MEDIUM: { label: 'Media', color: '#f39c12', width: '50%' },
  STRONG: { label: 'Fuerte', color: '#2ecc71', width: '75%' },
  VERY_STRONG: { label: 'Muy fuerte', color: '#27ae60', width: '100%' },
};

function StrengthBar({ level, entropy }) {
  const config = LEVEL_CONFIG[level] ?? LEVEL_CONFIG.WEAK;

  return (
    <div className="strength-bar-container">
      <div className="strength-bar-track">
        <div
          className="strength-bar-fill"
          style={{ width: config.width, backgroundColor: config.color }}
        />
      </div>
      <div className="strength-label">
        <span style={{ color: config.color }}>{config.label}</span>
        <span className="entropy">{entropy.toFixed(1)} bits</span>
      </div>
    </div>
  );
}

export default StrengthBar;