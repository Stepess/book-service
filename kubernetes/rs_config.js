cfg = rs.conf()
cfg.settings.getLastErrorDefaults = { w: 3, wtimeout: 5000 }
rs.reconfig(cfg)
