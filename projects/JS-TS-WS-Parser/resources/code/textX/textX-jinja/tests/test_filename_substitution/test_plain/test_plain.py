"""
Test plain variable substitution in file names
"""
import os
import shutil
from textxjinja import textx_jinja_generator


def test_basic():
    this_folder = os.path.dirname(__file__)
    template_folder = os.path.join(this_folder, 'templates')
    output_folder = os.path.join(this_folder, 'output')

    # Remove output folder
    try:
        shutil.rmtree(output_folder)
    except OSError:
        pass

    # Prepare config
    config = {}
    config['first'] = 'somevalue'

    # Generate
    textx_jinja_generator(template_folder, output_folder, config, overwrite=True)

    # Assert that file exists
    assert os.path.exists(os.path.join(output_folder, 'this_issomevaluefile.txt'))
